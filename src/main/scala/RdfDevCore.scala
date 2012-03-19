package jp.rough_diamond.dev.core

import sbt._
import Keys._
import java.io.{File, FileOutputStream}

object RdfDevCore extends Plugin {
	val PROJECT_SBT = "rdf.sbt"
	val PLUGIN_SBT = "project/rdfPlugin.sbt"
	lazy val rdf = config("rdf") /*extend(Test) hide*/
//	val updateBuild = TaskKey[Unit]("update-build")
	val init = TaskKey[Unit]("init", "RDF開発環境を初期化します")
	override val settings = inConfig(rdf)(Seq(
//			updateBuild <<= (baseDirectory) map { (baseDir) => doInit(baseDir) }
//			,init <<= Seq(updateBuild, reload).dependOn
		init <<= (baseDirectory) map { (baseDir) => doInit(baseDir) }
	)) ++ Seq(
		javacOptions 	++= Seq("-encoding", "UTF-8")
		,resolvers 		+= Resolver.url("Rough Diamond Framework Repository", url("http://133.242.22.208/repos/"))(Resolver.ivyStylePatterns)
	)

	def doInit(baseDir:File) = { 
		val f1 = new File(baseDir, PROJECT_SBT)
		println("updating..." + f1.getName())
		writeStringToFile(f1, projectContext)
		val f2 = new File(baseDir, PLUGIN_SBT)
		println("updating..." + f2.getName())
		writeStringToFile(f2, pluginContext)
	}
	
	def writeStringToFile(file:File, context:String) = {
		file.getParentFile().mkdirs();
		val os = new FileOutputStream(file)
		os.write(context.getBytes("UTF-8"))
		os.close()
	}

	val projectContext = 
"""
libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test -> default"

seq(jacoco.settings : _*)

testListeners <<= target.map(t => Seq(new eu.henkelmann.sbt.JUnitXmlTestsListener(t.getAbsolutePath)))
"""
	val pluginContext = 
"""
resolvers += Resolver.url("Rough Diamond Framework Repository", url("http://133.242.22.208/repos/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  "org.jacoco" % "org.jacoco.core" % "0.5.6.201201232323" artifacts(Artifact("org.jacoco.core", "jar", "jar")),
  "org.jacoco" % "org.jacoco.report" % "0.5.6.201201232323" artifacts(Artifact("org.jacoco.report", "jar", "jar")))

addSbtPlugin("de.johoop" % "jacoco4sbt" % "1.2.1")

libraryDependencies += "eu.henkelmann" % "junit_xml_listener" % "0.3"
"""
}
