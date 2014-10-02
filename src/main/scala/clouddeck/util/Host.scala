package clouddeck.util

import spray.json._
import DefaultJsonProtocol._

case class Host(name: String, nickname: Option[String] = None, description: Option[String] = None)

object HostJsonProtocol extends DefaultJsonProtocol {
  implicit val vmxFormat = jsonFormat3(Host)
}
