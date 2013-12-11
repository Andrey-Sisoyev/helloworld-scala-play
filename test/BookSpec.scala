import models.entity.Book
import backend.DBSchema

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import org.squeryl.PrimitiveTypeMode.inTransaction

import play.api.test._
import play.api.test.Helpers._

class BookSpec extends FlatSpec with ShouldMatchers {
  
  "A Book" should "be creatable" in {
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      inTransaction {
        val book = DBSchema.tblBooks insert Book("foo", 123)
        book.id should not equal(0)
      }
    }
  }
  
}
