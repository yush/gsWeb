import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "gsWeb"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
       javaCore, javaJdbc, javaEbean,
      "org.pegdown" % "pegdown" % "1.1.0"       
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
		resolvers += "Pegdown"at "http://mvnrepository.com/artifact/org.pegdown/pegdown" 
    )

}
