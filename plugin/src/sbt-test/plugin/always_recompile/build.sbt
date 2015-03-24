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

scalateSettings ++ Seq(
  scalateOverwrite := true
)
scalateTemplateConfig in Compile <<= (sourceDirectory in Compile) { base =>
  Seq(
    TemplateConfig(
      base / "templates",
      Nil,
      Nil
    )
  )
}

TaskKey[Unit]("recordModifiedTime") <<= (sourceManaged in Compile) map { (base) =>
  val recorded = base / "index_ssp.scala"
  IO.touch(recorded, true)
}

TaskKey[Unit]("checkCompiled") <<= (sourceManaged in Compile) map { (base) =>
  val generated = base / "scalate" / "templates" / "index_ssp.scala"
  if (!generated.exists) {
    error(s"${generated.getAbsolutePath} doesn't exist.")
  }
  ()
}

TaskKey[Unit]("checkRecompiled") <<= (sourceManaged in Compile) map { (base) =>
  val recorded = base / "index_ssp.scala"
  val generated = base / "scalate" / "templates" / "index_ssp.scala"
  if (!generated.exists) {
    error(s"${generated.getAbsolutePath} doesn't exist.")
  }
  if (recorded.lastModified > generated.lastModified) {
    error(s"${generated.getAbsolutePath} are not recompiled.")
  }
  ()
}
