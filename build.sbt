lazy val precompiler = project
lazy val plugin = project

scalacOptions ++= Seq("-unchecked", "-deprecation")
javacOptions ++= Seq("-target", "1.6", "-source", "1.6")
scalaVersion := "2.11.6"
crossScalaVersions := Seq("2.10.5", "2.11.6")
organization in ThisBuild := "org.skinny-framework"
licenses in ThisBuild := Seq("MIT" -> new URL("https://github.com/skinny-framework/sbt-scalate-precompiler/blob/master/LICENSE"))
publishMavenStyle := true
pomIncludeRepository := { x => false }
pomExtra := {
<url>https://github.com/skinny-framework/sbt-scalate-precompiler</url>
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
}

scalariformSettings
