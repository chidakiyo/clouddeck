package clouddeck.util

import spray.json._
import DefaultJsonProtocol._

case class Host(name: String)

object HostJsonProtocol extends DefaultJsonProtocol {
  implicit val vmxFormat = jsonFormat1(Host)
}
