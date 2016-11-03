lazy val precompiler = (project in file("precompiler")).settings(baseSettings).settings(
  name := "scalate-precompiler",
  libraryDependencies += "org.scalatra.scalate" %% "scalate-core" % "1.8.0-RC1" % "compile"
).settings(scalariformSettings)

lazy val plugin = (project in file("plugin")).settings(baseSettings).settings(
  name := "sbt-scalate-precompiler",
  sbtPlugin := true,
  sourceGenerators in Compile <+= (sourceManaged in Compile, name, organization, version) map { 
    (sourceManaged: File, name: String, vgp: String, buildVersion) => {
      val file = sourceManaged / vgp.replace(".","/") / "Version.scala"
      val code = { 
s"""package skinny.scalate
object Version {
  val name    = "${name}"
  val version = "${buildVersion}"
}
""".stripMargin
      }
      IO.write(file, code)
      Seq(file)
    }
  }
).settings(scalariformSettings)

lazy val baseSettings = Seq(
  organization := "org.skinny-framework",
  version := "1.8.0.0-RC1",
  transitiveClassifiers in Global := Seq(Artifact.SourceClassifier),
  parallelExecution in Test := false,
  logBuffered in Test := false,
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
  javacOptions ++= Seq("-target", "1.8", "-source", "1.8"),
  publishMavenStyle := true,
  pomIncludeRepository := { x => false },
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (version.value.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
    else Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  pomExtra := <url>https://github.com/skinny-framework/sbt-scalate-precompiler</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:skinny-framework/sbt-scalate-precompiler.git</url>
    <connection>scm:git:git@github.com:skinny-framework/sbt-scalate-precompiler.git</connection>
  </scm>
  <developers>
    <developer>
      <id>casualjim</id>
      <name>Ivan Porto Carrero</name>
      <url>http://flanders.co.nz/</url>
    </developer>
    <developer>
      <id>sdb</id>
      <name>Stefan De Boey</name>
      <url>http://stefandeboey.be/</url>
    </developer>
    <developer>
      <id>BowlingX</id>
      <name>David Heidrich</name>
      <url>http://www.myself-design.com/</url>
    </developer>
    <developer>
      <id>seratch</id>
      <name>Kazuhiro Sera</name>
      <url>http://git.io/sera</url>
    </developer>
  </developers>
)
