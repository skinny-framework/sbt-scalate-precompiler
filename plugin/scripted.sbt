ScriptedPlugin.scriptedSettings
scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}
scriptedBufferLog := false
resolvers ++= Seq(
  "sonatype releases"  at "https://oss.sonatype.org/content/repositories/releases",
  "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)
