package clouddeck.util

import org.specs2.mutable.Specification
import clouddeck.command.ConnectInfo
import spray.json._
import DefaultJsonProtocol._

class ResultSpec extends Specification {

  "Result" should {

    "success" in {
      import HostJsonProtocol._
      import ResultJsonProtocol._
      val r = Result(success = Some(Success(List(Host("localhost")))))
      println(r.toJson)
      r.toJson mustEqual """{"success":{"data":[{"name":"localhost"}]}}""".parseJson
    }

    "error" in {
      import HostJsonProtocol._
      import ResultJsonProtocol._
      val r = Result[Host](error = Some(Error("error!", "E001")))
      println(r.toJson)
      r.toJson mustEqual """{"error":{"message":"error!","code":"E001"}}""".parseJson
    }

    "success and error" in {
      import HostJsonProtocol._
      import ResultJsonProtocol._
      val r = Result(success = Some(Success(List(Host("localhost")))), error = Some(Error("error!", "E001")))
      println(r.toJson)
      r.toJson mustEqual """{"success":{"data":[{"name":"localhost"}]}, "error":{"message":"error!","code":"E001"}}""".parseJson
    }

  }

}
