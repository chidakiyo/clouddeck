package clouddeck.util

import spray.json._
import DefaultJsonProtocol._

object Response {

  def toVMX(line: String): VMX = {
    val columns = line.split("/")
    VMX(columns(columns.size - 2), columns(columns.size - 1), s"/${columns(1)}/${columns(2)}/${columns(3)}", line)
  }

  def isOn(line: String): Boolean = {
    val columns = line.replaceAll("""\s+""", "").split("=")
    columns(1) match {
      case "on" => true
      case "off" => false
      case _ => throw new RuntimeException(s"response cannot be parsed as status. ${line}")
    }
  }

}

case class VMX(name: String, image: String, storage: String, fullPath: String)

object VMXJsonProtocol extends DefaultJsonProtocol {
  implicit val vmxFormat = jsonFormat4(VMX)
}
