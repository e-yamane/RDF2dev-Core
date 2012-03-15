package jp.rough_diamond.dev.core

import org.scalatest.Spec
import java.io.File
import org.apache.commons.io.FileUtils._

class RdfDevCoreSpec extends Spec {
	val testDir = new File("testDir")
	deleteQuietly(testDir)
	testDir.mkdir()
	RdfDevCore.rdf.doInit(testDir)
	describe("initを実行すると") {
		it("指定したディレクトリ直下にrdf.sbtが作成されていないといけない") {
			 assert(new File(testDir, RdfDevCore.PROJECT_SBT).exists())
		}
		it("指定したディレクトリ直下にproject/rdfPlugins.sbtが作成作成されていないといけない") {
			 assert(new File(testDir, RdfDevCore.PLUGIN_SBT).exists())
		}
	}
}