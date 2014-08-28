package clouddeck.util

object Response {

  def getMachineName(line: String): String = {
    val columns = line.split("/")
    columns(columns.size - 2)
  }

}