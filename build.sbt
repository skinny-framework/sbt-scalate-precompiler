lazy val precompiler = (project in file("precompiler")).settings(baseSettings: _*).settings(
  name := "scalate-precompiler",
  libraryDependencies <+= (scalaVersion) {
    case v if v.startsWith("2.10") => "org.fusesource.scalate" %% "scalate-core" % "1.6.1" % "compile"
    case _                         => "org.scalatra.scalate"   %% "scalate-core" % "1.7.1" % "compile"
  }
).settings(scalariformSettings: _*)

lazy val plugin = (project in file("plugin")).settings(baseSettings: _*).settings(
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
).settings(scalariformSettings: _*)

lazy val baseSettings = Seq(
  organization := "org.skinny-framework",
  transitiveClassifiers in Global := Seq(Artifact.SourceClassifier),
  parallelExecution in Test := false,
  logBuffered in Test := false,
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
  javacOptions ++= Seq("-target", "1.6", "-source", "1.6"),
  licenses := Seq("MIT" -> new URL("https://github.com/skinny-framework/sbt-scalate-precompiler/blob/master/LICENSE")),
  publishMavenStyle := true,
  pomIncludeRepository := { x => false },
  publishTo <<= version { (v: String) =>
    val nexus = "https://oss.sonatype.org/"
    if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
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
