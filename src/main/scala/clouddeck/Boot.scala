package clouddeck

import akka.actor.{ ActorSystem, Props }
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import clouddeck.service.MyServiceActor
import java.io.File

object Boot extends App {
  implicit val system = ActorSystem("on-spray-can")
  val service = system.actorOf(Props[MyServiceActor], "clouddeck-service")
  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)

  //  List(new File(_root_.util.Directory.AppHome)).filter(!_.exists).foreach(_.mkdirs)
}
