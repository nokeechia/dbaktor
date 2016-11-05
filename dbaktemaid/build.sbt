name := """dbaktemaid"""

//organization := "com.dbaktor"
//version := "0.0.1-SNAPSHOT"

//scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
//libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
//libraryDependencies ++= Seq(
//  "com.dbaktor"   %% "dbaktor"     % "0.0.1-SNAPSHOT",
//  "com.typesafe.akka" % "akka-http-experimental_2.11" % "1.0-M4",
//  "com.typesafe.akka" % "akka-http-core-experimental_2.11" % "1.0-M4",
//  "com.syncthemall" % "boilerpipe" % "1.2.2",
//  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
//  "com.typesafe.akka" %% "akka-testkit" % "2.3.6" % "test"
//)
// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"
libraryDependencies ++= Seq(
  "com.dbaktor"   %% "server-db"     % "0.0.1-SNAPSHOT",
  "com.syncthemall" % "boilerpipe" % "1.2.2"
 )

mappings in (Compile, packageBin) ~= { _.filterNot { case (_, name) =>
  Seq("application.conf").contains(name)
}}
