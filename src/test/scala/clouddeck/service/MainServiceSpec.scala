package clouddeck.service

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import spray.http.StatusCodes._

class MyServiceSpec extends Specification with Specs2RouteTest with MainService {
  def actorRefFactory = system

  "MainService" should {

    "return a greeting for GET requests to the root path" in {
      Get() ~> myRoute ~> check {
        responseAs[String] must contain("Result")
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> myRoute ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(myRoute) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}
