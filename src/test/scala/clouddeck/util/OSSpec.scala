package clouddeck.util

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest

class OSSpec extends Specification with Specs2RouteTest {

  "OS" should {

    "" in {
      println(System.getProperty("os.name"))
      println(System.getProperty("os.arch"))
      println(OS())
      "" must contain("")
    }

  }

  "OSUtil" should {
    // for windows environment
    "isWindows" in {
      OSUtil.isWindows must be_==(true)
    }
  }

}