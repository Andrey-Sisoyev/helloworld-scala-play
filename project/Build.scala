import sbt._
import Keys._
import play.Project._

object WCtlBooksBuild extends Build {

    val appName         = "play2bars"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq( 
          "org.scalatest" %% "scalatest" % "1.9.2" % "test"
        , "org.squeryl" %% "squeryl" % "0.9.5-6"
        , "org.postgresql" % "postgresql" % "9.2-1002-jdbc4"
        , "org.webjars" % "jquery" % "1.10.2"
        // , "com.typesafe.play" %% "play-jdbc" % "2.2.1"
        )

    // http://repo.typesafe.com/typesafe/maven-releases/com/typesafe/play/play-jdbc_2.10/2.2.1/play-jdbc_2.10-2.2.1.pom

    val main = play.Project(appName, appVersion, appDependencies)
        .settings(
          resolvers += "webjars" at "http://webjars.github.com/m2",
          resolvers += "typesafe repo1-cache" at "http://repo.typesafe.com/typesafe/repo1-cache/",
          resolvers += "typesafe maven-releases" at "http://repo.typesafe.com/typesafe/maven-releases/",
          
          testOptions in Test := Nil
          // Add your own project settings here
        )

}
