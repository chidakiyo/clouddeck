package clouddeck.util

import clouddeck.command.ConnectInfo
import util.Directory
import scala.io.Source
import util.ControlUtil._

object ConfigUtil {

  def find(host: String): Option[ConnectInfo] = JsonUtil.parseNamedConnectInfosJson(conf()).root.find(_.host == host)

  def find(): List[ConnectInfo] = JsonUtil.parseNamedConnectInfosJson(conf()).root

  def conf(): String = {
    using(Source.fromFile(Directory.AppConf)) { source =>
      source.mkString
    }
  }

}
