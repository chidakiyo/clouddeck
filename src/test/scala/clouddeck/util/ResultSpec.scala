package clouddeck.util

import org.specs2.mutable.Specification
import clouddeck.command.ConnectInfo
import spray.json._
import DefaultJsonProtocol._

class ResultSpec extends Specification {

  "Result" should {

    "success" in {
      import ResultJsonProtocol._
      val r = Result(success = Some(Success(List(ConnectInfo("localhost", "admin", "pass")))))
      println(r.toJson)
      r.toJson mustEqual """{"success":{"data":[{"host":"localhost","user":"admin","pass":"pass"}]}}""".parseJson
    }

    "error" in {
      import ResultJsonProtocol._
      val r = Result(error = Some(Error("error!", "E001")))
      println(r.toJson)
      r.toJson mustEqual """{"error":{"message":"error!","code":"E001"}}""".parseJson
    }

    "success and error" in {
      import ResultJsonProtocol._
      val r = Result(success = Some(Success(List(ConnectInfo("localhost", "admin", "pass")))), error = Some(Error("error!", "E001")))
      println(r.toJson)
      r.toJson mustEqual """{"success":{"data":[{"host":"localhost","user":"admin","pass":"pass"}]}, "error":{"message":"error!","code":"E001"}}""".parseJson
    }

  }

}
