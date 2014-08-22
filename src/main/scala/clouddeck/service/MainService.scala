package clouddeck.service

import akka.actor.Actor
import spray.routing._
import spray.http._
import spray.http.MediaTypes._
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply

class MyServiceActor extends Actor with MainService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

trait MainService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <html>
              <body>
                <h1>Result: ---</h1>
              </body>
            </html>
          }
        }
      }
    }
}