import skinny.scalate.ScalatePlugin._
import ScalateKeys._

version := "0.1"
scalaVersion := "2.12.1"

resolvers += Resolver.file("ivy-local", file(Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.mavenStylePatterns)
resolvers ++= Seq(
  "sonatype releases"  at "https://oss.sonatype.org/content/repositories/releases",
  "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies += "org.scalatra.scalate" %% "scalate-core" % "1.8.0" % "compile"

scalateSettings
scalateTemplateConfig in Compile := {
  val base = (sourceDirectory in Compile).value
  Seq(
    TemplateConfig(
      base / "templates",
      Nil,
      Nil
    )
  )
}
TaskKey[Unit]("check") := {
  val outputDir = (sourceManaged in Compile).value
  val scalaFile = outputDir / "scalate" / "templates" / "index_ssp.scala"
  if (!scalaFile.exists) {
    sys.error(s"${scalaFile.getAbsolutePath} doesn't exist.")
  }
  ()
}
