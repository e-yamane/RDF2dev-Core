name := "rdf2-dev-core"

description := "Rough Diamond Framework Development Environment Core"

organization := "jp.rough_diamond"

version := "2.0.3"

scalaVersion := "2.9.1"

sbtPlugin := true 

javacOptions ++= Seq("-encoding", "UTF-8")

libraryDependencies +=  "junit"                 %   "junit"             % "4.10"    % "test"

libraryDependencies +=  "commons-io"            %   "commons-io"        % "2.1"     % "test"

libraryDependencies +=  "com.novocode"          %   "junit-interface"   % "0.8"     % "test -> default"

libraryDependencies +=  "org.scalatest"         %%  "scalatest"         % "1.7.1"   % "test"

seq(jacoco.settings : _*)

testListeners <<= target.map(t => Seq(new eu.henkelmann.sbt.JUnitXmlTestsListener(t.getAbsolutePath)))