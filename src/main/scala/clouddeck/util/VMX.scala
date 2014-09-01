package clouddeck.util

import spray.json._
import DefaultJsonProtocol._

case class VMX(name: String, image: String, storage: String, fullPath: String, isOn: Option[Boolean] = None)

object VMXJsonProtocol extends DefaultJsonProtocol {
  implicit val vmxFormat = jsonFormat5(VMX)
}
