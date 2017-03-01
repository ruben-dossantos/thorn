organization := "thorn"

name := "thorn"

version := "0.1-SNAPSHOT"

val unfilteredVersion = "0.9.0"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "io.argonaut"             %% "argonaut"                         % "6.1",
  "com.typesafe.akka"       %% "akka-actor"                       % "2.4.17",
  "com.typesafe.akka"       %% "akka-http"                        % "10.0.3",
  "com.typesafe.akka"       %% "akka-http-spray-json"             % "10.0.3",
  "com.typesafe.slick"      %% "slick"                            % "3.1.1",
  "com.typesafe.slick"      %  "slick-hikaricp_2.11"              % "3.1.1",
  "org.slf4j"               %  "slf4j-nop"                        % "1.6.4",
  "org.postgresql"          %  "postgresql"                       % "9.4-1201-jdbc41",
  "com.zaxxer"              %  "HikariCP"                         % "2.6.0"
)