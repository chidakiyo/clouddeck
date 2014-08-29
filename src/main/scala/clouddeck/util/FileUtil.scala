package clouddeck.util

import java.io.File
import scala.io.Source
import util.ControlUtil._
import java.io.FileWriter
import java.io.PrintWriter
import util.Directory
import clouddeck.command.ConnectInfo

object FileUtil {

  def readAll(file: File): String = using(Source.fromFile(file)) { f => f.mkString }

  def write(file: File, data: String): Unit = using(new FileWriter(file)) { fw => fw.write(data) }

}
