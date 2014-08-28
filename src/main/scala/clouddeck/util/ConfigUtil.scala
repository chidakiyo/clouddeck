package clouddeck.util

import FileUtil.readAll
import JsonUtil.parseNamedConnectInfosJson
import clouddeck.command.ConnectInfo
import util.Directory

object ConfigUtil {

  def find(host: String): Option[ConnectInfo] = connectInfos().find(_.host == host)

  def find(): List[ConnectInfo] = connectInfos()

  def conf(): String = readAll(Directory.AppConf)

  def connectInfos(): List[ConnectInfo] = parseNamedConnectInfosJson(conf()).root
}
