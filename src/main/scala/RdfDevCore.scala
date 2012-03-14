package jp.rough_diamond.dev.core

import sbt._
import Keys._

//TODO 以下の記述を書きたすのは将来的に不便だから緩和したい。。。
//build.sbtに「seq(de.johoop.jacoco4sbt.JacocoPlugin.jacoco.settings : _*)」
//plugins.sbtに「addSbtPlugin("de.johoop" % "jacoco4sbt" % "1.2.1")」

object RdfDevCore extends Plugin {
	override val settings : Seq[Setting[_]] = Seq(
		javacOptions 		++= Seq("-encoding", "UTF-8"),
		libraryDependencies ++= Seq(
			 "com.novocode"		% "junit-interface"			% "0.8"	% "test -> default"
			,"eu.henkelmann" 	% "junit_xml_listener" 		% "0.3" % "test"
			,"org.jacoco" 		% "org.jacoco.core" 		% "0.5.6.201201232323" % "test" artifacts(Artifact("org.jacoco.core", "jar", "jar")) 
  			,"org.jacoco" 		% "org.jacoco.report" 		% "0.5.6.201201232323" % "test" artifacts(Artifact("org.jacoco.report", "jar", "jar")) 
  		)
	)
}
