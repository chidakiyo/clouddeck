package util

import java.io.File
import util.ControlUtil._
import org.apache.commons.io.FileUtils
import java.lang.System._

object Directory {

  val AppHome = (getProperty("clouddeck.home") match {
    case path if (path != null) => new File(path)
    case _ => scala.util.Properties.envOrNone("CLOUDDECK_HOME") match {
      case Some(env) => new File(env)
      case None => {
        new File(getProperty("user.home"), ".clouddeck")
      }
    }
  }).getAbsolutePath

  val AppConf = new File(AppHome, "clouddeck.conf")

}