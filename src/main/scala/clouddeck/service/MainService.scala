package clouddeck.service

import akka.actor.Actor
import spray.routing._
import spray.http._
import spray.http.MediaTypes._
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply

import spray.json._
import DefaultJsonProtocol._

class MyServiceActor extends Actor with MainService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

trait MainService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            val jsonAst = List(1, 2, 3).toJson
            jsonAst.prettyPrint
          }
        }
      }
    }
}