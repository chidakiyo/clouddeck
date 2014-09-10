package clouddeck.service

import akka.actor.Actor
import spray.routing._
import spray.http._
import spray.http.MediaTypes._
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import spray.routing.directives.CachingDirectives._
import spray.json._
import DefaultJsonProtocol._
import clouddeck.command.ConnectInfo
import clouddeck.command.Commands
import clouddeck.parser.Parser
import clouddeck.util.ConfigUtil
import clouddeck.util.Expression._
import java.io.File
import clouddeck.Keys

class MyServiceActor extends Actor with MainService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

trait MainService extends HttpService {

  val myRoute = {
    // ESXi host list of all
    path("hosts") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            hosts()
          }
        }
      }
    } ~
      // Guest list of all
      path("guests" / """.+""".r) { id =>
        get {
          respondWithMediaType(`application/json`) {
            complete {
              guests(id)
            }
          }
        }
      } ~
      // Guest state
      path("state" / """.+""".r / """.+""".r) { (id, guestId) => // id : host name, guestId : guest name
        get {
          respondWithMediaType(`application/json`) {
            complete {
              state(id, guestId)
            }
          }
        }
      } ~
      // Static contents
      pathPrefix("scripts") {
        getFromBrowseableDirectory(s"${Keys.Const.contentroot}scripts")
      } ~
      pathPrefix("bower_components") {
        getFromBrowseableDirectory(s"${Keys.Const.contentroot}bower_components")
      } ~
      pathPrefix("styles") {
        getFromBrowseableDirectory(s"${Keys.Const.contentroot}styles")
      } ~
      path("") {
        getFromFile(new File(s"${Keys.Const.contentroot}index.html"))
      }
  }
}
