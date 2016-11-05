name := """client"""

//organization := "com.dbaktor"
//version := "0.0.1-SNAPSHOT"
//version := "1.0"

//scalaVersion := "2.11.7"

// Change this to another test framework if you prefer

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-remote" % "2.3.6",
  "com.dbaktor"   %% "server-db"     % "0.0.1-SNAPSHOT"
)

mappings in (Compile, packageBin) ~= { _.filterNot { case (_, name) =>
  Seq("application.conf").contains(name)
}}