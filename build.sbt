name := """dbaktor"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq( "com.typesafe.akka" %% "akka-actor" % "2.3.3", "com.typesafe.akka" %% "akka-testkit" % "2.3.6" % "test", "org.scalatest" %% "scalatest" % "2.1.6" % "test")

