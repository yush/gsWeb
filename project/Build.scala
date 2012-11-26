import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "gsWeb"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
       javaCore, javaJdbc, javaEbean
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(

    )

}
