import controllers.routes
import models.entity.Book
import backend.DBSchema

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import org.squeryl.PrimitiveTypeMode.inTransaction

import play.api.http.ContentTypes.JSON
import play.api.test._
import play.api.test.Helpers._

class WCtlBooksSpec extends FlatSpec with ShouldMatchers {

  "A request to the addBook action" should "respond" in {
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      val result = controllers.WCtlBooks.addBook(FakeRequest().withFormUrlEncodedBody("name" -> "FooBook"))
      status(result) should equal (SEE_OTHER)
      redirectLocation(result) should equal (Some(routes.WCtlBooks.index.url))
    }
  }
  
  "A request to the getBooks Action" should "respond with data" in {
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      inTransaction(DBSchema.barTable insert Book(Some("foo")))

      val result = controllers.WCtlBooks.getBooks(FakeRequest())
      status(result) should equal (OK)
      contentAsString(result) should include("foo")
    }
  }
  
}