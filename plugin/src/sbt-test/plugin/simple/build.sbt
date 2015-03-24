import skinny.scalate.ScalatePlugin._
import ScalateKeys._

version := "0.1"
scalaVersion := "2.10.4"

resolvers += Resolver.file("ivy-local", file(Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.mavenStylePatterns)
resolvers ++= Seq(
  "sonatype releases"  at "https://oss.sonatype.org/content/repositories/releases",
  "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies += "org.scalatra.scalate" %% "scalate-core" % "1.7.1" % "compile"

scalateSettings
scalateTemplateConfig in Compile <<= (sourceDirectory in Compile) { base =>
  Seq(
    TemplateConfig(
      base / "templates",
      Nil,
      Nil
    )
  )
}
TaskKey[Unit]("check") <<= (sourceManaged in Compile) map { (outputDir) =>
  val scalaFile = outputDir / "scalate" / "templates" / "index_ssp.scala"
  if (!scalaFile.exists) {
    error(s"${scalaFile.getAbsolutePath} doesn't exist.")
  }
  ()
}
