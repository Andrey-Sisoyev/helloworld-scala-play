package backend

import models.entity._
import org.squeryl.Schema
import org.squeryl.PrimitiveTypeMode._

object DBSchema extends Schema {
  val tblBooks = table[Book]("books")
  val tblReadCoverages = table[ReadCoverage]("coverage")

  on(tblBooks)(book => declare(
    book.id is(autoIncremented("s_book_id"))
  ))

  on(tblReadCoverages)(rc => declare(
    rc.id is(autoIncremented("s_coverage_id"))
  ))


  val fkReadCoverages_toBooks =
    oneToManyRelation(tblBooks, tblReadCoverages).
    via((b,rc) => b.id === rc.book_id) 
}