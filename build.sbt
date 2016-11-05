name := """dbaktor"""


val commonSettings = Seq(
  organization := "com.dbaktor",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.11.7",
  libraryDependencies ++= Seq( "com.typesafe.akka" %% "akka-actor" % "2.3.3",
    "com.typesafe.akka" %% "akka-testkit" % "2.3.6" % "test",
    "org.scalatest" %% "scalatest" % "2.1.6" % "test",
    "com.typesafe.akka" %% "akka-remote" % "2.3.6"
  )
)

lazy val server_db = project.in(file("server-db")).settings(commonSettings: _*)

lazy val client = project.in(file("client")).settings(commonSettings: _*)

lazy val dbaktemaid = project.in(file("dbaktemaid")).settings(commonSettings: _*)

lazy val root = project.in(file(".")).aggregate(server_db,client,dbaktemaid)
// Change this to another test framework if you prefer

mappings in (Compile, packageBin) ~= { _.filterNot { case (_, name) =>
  Seq("application.conf").contains(name)
}}



fork in run := true

fork in run := true