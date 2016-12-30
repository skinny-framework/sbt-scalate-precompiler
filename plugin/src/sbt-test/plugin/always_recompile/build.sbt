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

scalateSettings ++ Seq(
  scalateOverwrite := true
)
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

TaskKey[Unit]("recordModifiedTime") := {
  val base = (sourceManaged in Compile).value
  val recorded = base / "index_ssp.scala"
  IO.touch(recorded, true)
}

TaskKey[Unit]("checkCompiled") := {
  val base = (sourceManaged in Compile).value
  val generated = base / "scalate" / "templates" / "index_ssp.scala"
  if (!generated.exists) {
    sys.error(s"${generated.getAbsolutePath} doesn't exist.")
  }
  ()
}

TaskKey[Unit]("checkRecompiled") := {
  val base = (sourceManaged in Compile).value
  val recorded = base / "index_ssp.scala"
  val generated = base / "scalate" / "templates" / "index_ssp.scala"
  if (!generated.exists) {
    sys.error(s"${generated.getAbsolutePath} doesn't exist.")
  }
  if (recorded.lastModified > generated.lastModified) {
    sys.error(s"${generated.getAbsolutePath} are not recompiled.")
  }
  ()
}
