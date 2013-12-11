package models.entity

import play.api.libs.json._
import org.squeryl.annotations.Column
import org.squeryl.KeyedEntity
import org.squeryl.dsl.{ManyToOne, OneToMany}
import java.sql.Timestamp
import backend.DBSchema

case class Book 
      ( name: String
      , @Column("words_count") wordsCount: Long
      ) 
      extends KeyedEntity[Int] {  
    var id: Int = 0;
    def this(id0: Int, name: String, wordsCount: Long) { 
        this(name,wordsCount)
        id = id0
    }
    
    lazy val courses: OneToMany[ReadCoverage] = DBSchema.fkReadCoverages_toBooks.left(this)
}

object Book {
    implicit object BookFormat extends Format[Book] {

      def reads(json: JsValue): JsResult[Book] = 
        JsSuccess[Book](
            (json \ "id") match {
                case _: JsUndefined =>
                    Book( (json \ "name").as[String]
                        , (json \ "wordsCount").as[Long]
                        )
                case jsvId: JsValue =>
                    new Book( 
                          jsvId.as[Int]
                        , (json \ "name").as[String]
                        , (json \ "wordsCount").as[Long]
                        )
            }
        )
            

      def writes(u: Book): JsValue = 
        JsObject(
            List( "id" -> JsNumber(u.id)
                , "name" -> JsString(u.name)
                , "wordsCount" -> JsNumber(u.wordsCount)
                )
        )

    }
}