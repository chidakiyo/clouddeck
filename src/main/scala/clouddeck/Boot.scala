package clouddeck

import java.io.File
import scala.concurrent.duration.DurationInt
import akka.actor.ActorSystem
import akka.actor.Props
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import clouddeck.service.MyServiceActor
import spray.can.Http
import clouddeck.util.ConfigUtil

object Boot extends App {
  implicit val system = ActorSystem("on-spray-can")
  val service = system.actorOf(Props[MyServiceActor], "clouddeck-service")
  implicit val timeout = Timeout(100.seconds)
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 80)

  List(new File(_root_.util.Directory.AppHome)).filter(!_.exists).foreach(_.mkdirs)
  List(_root_.util.Directory.AppConf).filter(!_.exists).foreach(ConfigUtil.init(_))
}
