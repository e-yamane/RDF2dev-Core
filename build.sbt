name := "rdf2-dev-core"

description := "Rough Diamond Framework Development Environment Core"

organization := "jp.rough_diamond"

version := "2.0.1"

scalaVersion := "2.9.1"

sbtPlugin := true 

javacOptions ++= Seq("-encoding", "UTF-8")

libraryDependencies +=  "junit"                 %   "junit"             % "4.10"    % "test"

libraryDependencies +=  "com.novocode"          %   "junit-interface"   % "0.8"     % "test -> default"

seq(jacoco.settings : _*)

testListeners <<= target.map(t => Seq(new eu.henkelmann.sbt.JUnitXmlTestsListener(t.getAbsolutePath)))