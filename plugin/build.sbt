name := "sbt-scalate-precompiler"
scalacOptions ++= Seq("-unchecked", "-deprecation")
javacOptions ++= Seq("-target", "1.6", "-source", "1.6")
sbtPlugin := true
publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
scalaVersion := "2.10.4"
resolvers += Resolver.url("Typesafe repository", new java.net.URL("http://typesafe.artifactoryonline.com/typesafe/ivy-releases/"))(Resolver.defaultIvyPatterns)
sourceGenerators in Compile <+= (sourceManaged in Compile, name, organization, version) map { 
  (sourceManaged:File, name:String, vgp:String, buildVersion) =>
    val file = sourceManaged / vgp.replace(".","/") / "Version.scala"
    val code = { 
      "package skinny.scalate\n" +
      "object Version {\n" + 
      "  val name\t= \"" + name + "\"\n" + 
      "  val version\t= \"" + buildVersion + "\"\n" + 
      "}\n"  
    }
    IO.write(file, code)
    Seq(file)
}
