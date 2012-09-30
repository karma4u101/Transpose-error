
scalaVersion := "2.9.1"

resolvers ++= Seq(
  "Sonatype snapshots"             at "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype releases"              at "http://oss.sonatype.org/content/repositories/releases",
  "Java.net Maven2 Repository"     at "http://download.java.net/maven/2/"
)

libraryDependencies ++= {
  val liftVersion = "2.5-M1" // Put the current/latest lift version here
  Seq(
    "net.liftweb"      %% "lift-squeryl-record"  % liftVersion
    )
}
