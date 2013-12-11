import org.squeryl.adapters.{H2Adapter, PostgreSqlAdapter}
import org.squeryl.internals.DatabaseAdapter
import org.squeryl.{Session, SessionFactory}
import play.api.GlobalSettings
import java.sql.DriverManager

import play.api.Application

object Global extends GlobalSettings {
 
  override def onStart(app: Application) {
    Class.forName("org.postgresql.Driver")

    SessionFactory.concreteFactory = 
        Some(() => getSession(app))
  }

  def getSession(app: Application) = { 
    val dbUrl      = app.configuration.getString("db.default.url")     .get
    val dbUser     = app.configuration.getString("db.default.user")    .get
    val dbPassword = app.configuration.getString("db.default.password").get

    Session.create(DriverManager.getConnection(dbUrl, dbUser, dbPassword), new PostgreSqlAdapter)

  }

}