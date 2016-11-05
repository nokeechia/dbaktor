name := """server-db"""
//organization := "com.dbaktor"
//version := "0.0.1-SNAPSHOT"

//scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-remote" % "2.3.6"
)

mappings in (Compile, packageBin) ~= { _.filterNot { case (_, name) =>
  Seq("application.conf").contains(name)
}}



fork in run := true