package clouddeck.util

object Response {

  def toVMX(line: String): VMX = {
    val columns = line.split("/")
    VMX(columns(columns.size - 2), columns(columns.size - 1), s"/${columns(1)}/${columns(2)}/${columns(3)}", line)
  }

  def isOn(line: String): Boolean = {
    val columns = line.split("=")
    columns(1).trim match {
      case "on" => true
      case "off" => false
      case _ => throw new RuntimeException(s"response cannot be parsed as status. ${line}")
    }
  }

}
