package jp.rough_diamond.dev.core

import sbt._
import Keys._
import java.io.{File, FileOutputStream}

object RdfDevCore extends Plugin {
	val PROJECT_SBT = "rdf.sbt"
	val PLUGIN_SBT = "project/rdfPlugin.sbt"
	object rdf {
		lazy val ns = config("rdf") /*extend(Test) hide*/
		val updateBuild = TaskKey[Unit]("update-build")
		val init = TaskKey[Unit]("init", "RDF開発環境を初期化します")
		val settings = inConfig(ns)(Seq(
//			updateBuild <<= (baseDirectory) map { (baseDir) => doInit(baseDir) }
//			,init <<= Seq(updateBuild, reload).dependOn
			init <<= (baseDirectory) map { (baseDir) => doInit(baseDir) }
		))
		
		def doInit(baseDir:File) = { 
			println("doInit!")
			val f1 = new File(baseDir, PROJECT_SBT)
			writeStringToFile(f1, projectContext)
			val f2 = new File(baseDir, PLUGIN_SBT)
			writeStringToFile(f2, pluginContext)
		}
		
		def writeStringToFile(file:File, context:String) = {
			file.getParentFile().mkdirs();
			val os = new FileOutputStream(file)
			os.write(context.getBytes("UTF-8"))
			os.close()
		}
	}
	
	println("zzz");
	override val settings : Seq[Setting[_]] = Seq(
		javacOptions 	++= Seq("-encoding", "UTF-8")
		,resolvers 		+= "Rough Diamond Framework Repository" at "http://133.242.22.208/repos/"
	)

	val projectContext = 
"""
libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test -> default"

seq(rdf.settings : _*)

seq(jacoco.settings : _*)

testListeners <<= target.map(t => Seq(new eu.henkelmann.sbt.JUnitXmlTestsListener(t.getAbsolutePath)))
"""
	val pluginContext = 
"""
libraryDependencies ++= Seq(
  "org.jacoco" % "org.jacoco.core" % "0.5.6.201201232323" artifacts(Artifact("org.jacoco.core", "jar", "jar")),
  "org.jacoco" % "org.jacoco.report" % "0.5.6.201201232323" artifacts(Artifact("org.jacoco.report", "jar", "jar")))

addSbtPlugin("de.johoop" % "jacoco4sbt" % "1.2.1")

libraryDependencies += "eu.henkelmann" % "junit_xml_listener" % "0.3"
"""
}
