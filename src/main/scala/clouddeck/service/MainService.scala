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

class MyServiceActor extends Actor with MainService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

trait MainService extends HttpService {

  val myRoute = {
    path("") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            val jsonAst = List(1, 2, 3).toJson
            jsonAst.prettyPrint
          }
        }
      }
    } ~
      path("list" / """.+""".r) { id =>
        get {
          respondWithMediaType(`application/json`) {
            complete {

              // TODO Sample
              val user = scala.util.Properties.envOrElse("CCC_USER", "")
              val pass = scala.util.Properties.envOrElse("CCC_PASS", "")

              val info = ConnectInfo(id, user, pass)
              val cmd = Commands.VMWARE_CMD.cmdAndOpt(info) + " -l"
              println(cmd)

              import scala.sys.process._
              val hosts = Parser.images(cmd.!!)

              val jsonAst = hosts.toJson
              jsonAst.prettyPrint
            }
          }
        }
      }
  }
}
