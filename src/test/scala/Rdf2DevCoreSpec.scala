package jp.rough_diamond.dev.core

import org.scalatest.Spec
import java.io.File
import org.apache.commons.io.FileUtils._

class RdfDevCoreSpec extends Spec {
	describe("initを実行すると") {
		val testDir = new File("testDir")
		deleteQuietly(testDir)
		testDir.mkdir()
		RdfDevCore.doInit(testDir)
		it("指定したディレクトリ直下にrdf.sbtが作成されていないといけない") {
			 assert(new File(testDir, RdfDevCore.PROJECT_SBT).exists())
		}
		it("指定したディレクトリ直下にproject/rdfPlugins.sbtが作成作成されていないといけない") {
			 assert(new File(testDir, RdfDevCore.PLUGIN_SBT).exists())
		}
	}
}