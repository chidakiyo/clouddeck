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

import com.typesafe.scalalogging.slf4j._

class MyServiceActor extends Actor with MainService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

trait MainService extends HttpService with Logging {

  val myRoute = {
    // ESXi host list of all
    path("api" / "hosts") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            logger.info("hosts")
            hosts()
          }
        }
      }
    } ~
      // Guest list of all
      path("api" / "guests" / """.+""".r) { id =>
        get {
          respondWithMediaType(`application/json`) {
            complete {
              logger.info(s"guests/$id")
              guests(id)
            }
          }
        }
      } ~
      // Guest state
      path("api" / "state" / """.+""".r / """.+""".r) { (id, guestId) => // id : host name, guestId : guest name
        get {
          respondWithMediaType(`application/json`) {
            complete {
              logger.info(s"api/state/$id/$guestId")
              state(id, guestId)
            }
          }
        }
      } ~
      // Guest power on
      path("api" / "start" / """.+""".r / """.+""".r) { (id, guestId) => // id : host name, guestId : guest name
        get {
          respondWithMediaType(`application/json`) {
            complete {
              logger.info(s"api/start/$id/$guestId")
              start(id, guestId)
            }
          }
        }
      } ~
      // Guest power off
      path("api" / "stop" / """.+""".r / """.+""".r) { (id, guestId) => // id : host name, guestId : guest name
        get {
          respondWithMediaType(`application/json`) {
            complete {
              logger.info(s"api/stop/$id/$guestId")
              stop(id, guestId)
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
      pathPrefix("images") {
        getFromBrowseableDirectory(s"${Keys.Const.contentroot}images")
      } ~
      path("") {
        getFromFile(new File(s"${Keys.Const.contentroot}index.html"))
      }
  }
}
