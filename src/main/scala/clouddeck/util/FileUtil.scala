package clouddeck.util

import java.io.File

import scala.io.Source

import util.ControlUtil.using

object FileUtil {

  def readAll(file: File): String = using(Source.fromFile(file)) { f => f.mkString }

}
