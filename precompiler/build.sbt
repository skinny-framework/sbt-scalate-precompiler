name := "scalate-precompiler"
scalacOptions ++= Seq("-unchecked", "-deprecation")
javacOptions ++= Seq("-target", "1.6", "-source", "1.6")
scalaVersion := "2.11.6"
crossScalaVersions := Seq("2.10.5", "2.11.6")
libraryDependencies <+= (scalaVersion) {
  case v if v.startsWith("2.10") => "org.fusesource.scalate" %% "scalate-core" % "1.6.1" % "compile"
  case _                         => "org.scalatra.scalate"   %% "scalate-core" % "1.7.1" % "compile"
}
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.10"

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
