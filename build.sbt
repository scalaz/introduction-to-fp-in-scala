name := "introduction-to-fp-in-scala"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "org.scalaz"        %% "scalaz-core"                  % "7.2.27"
, "org.scalaz"        %% "scalaz-scalacheck-binding"    % "7.2.27-scalacheck-1.14" % "test"
, "org.scalaz.stream" %% "scalaz-stream"                % "0.8.6a"
, "org.specs2"        %% "specs2"                       % "3.8.9"    % "test" exclude("org.specs2", "specs2-cats_2.12")
, "org.scalacheck"    %% "scalacheck"                   % "1.14.0"   % "test"
)

resolvers ++= Seq(
  "oss snapshots"       at "http://oss.sonatype.org/content/repositories/snapshots"
, "oss releases"        at "http://oss.sonatype.org/content/repositories/releases"
, "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
)

scalacOptions := Seq(
  "-deprecation"
, "-unchecked"
, "-Xfatal-warnings"
, "-Xlint:_,-unused"
, "-feature"
, "-language:_"
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.8")
