package models.entity

import play.api.libs.json._
import org.squeryl.annotations.Column
import org.squeryl.KeyedEntity
import org.squeryl.dsl.{ManyToOne, OneToMany}
import java.sql.Timestamp
import backend.DBSchema

case class ReadCoverage 
      ( book_id: Int
      , user_id: Int
      , start_pos: Long
      , end_pos: Long
      , @Column("ts") whenRead: Timestamp
      ) 
      extends KeyedEntity[Long] {  
    var id: Long = 0;
    
    lazy val subject: ManyToOne[Book] = DBSchema.fkReadCoverages_toBooks.right(this)
}