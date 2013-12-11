package controllers

import play.api.mvc._

import play.api.libs.json._
import play.api.data.Form
import play.api.data.Forms.{mapping, text, optional, longNumber, nonEmptyText}

import org.squeryl.PrimitiveTypeMode._
import models.entity.Book
import backend.DBSchema

object WCtlBooks extends Controller {

  val bookForm = Form(
    mapping(
        "name" -> nonEmptyText.verifying(
            "validation.idontlikethisname", (value => value != "12345")
        )
      , "wordsCount" -> longNumber(min=1)
    )(Book.apply)(Book.unapply)
  )

  def index = Action {
    Ok(views.html.books(bookForm))
  }

  def getBooks = Action {
    val json = inTransaction {
      val books = from(DBSchema.tblBooks)(tblBooks =>
        select(tblBooks)
      )
      Json.toJson(books)
    }
    
    Ok(json).as(JSON)
  }

  def addBook = Action { implicit request =>
    bookForm.bindFromRequest.value map { book =>
      inTransaction(DBSchema.tblBooks insert book)
      Redirect(routes.WCtlBooks.index())
    } getOrElse BadRequest
  }
  
}