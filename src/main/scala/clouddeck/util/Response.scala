package clouddeck.util

object Response {

  def toVMX(line: String): VMX = {
    val columns = line.split("/")
    VMX(columns(columns.size - 2), columns(columns.size - 1), columns(columns.size - 3), line)
  }

}

case class VMX(name: String, image: String, storage: String, fullPath: String)
