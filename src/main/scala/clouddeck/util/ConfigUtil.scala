package clouddeck.util

import JsonUtil.parseNamedConnectInfosJson
import clouddeck.command.ConnectInfo
import util.Directory
import java.io.File

object ConfigUtil {

  def find(host: String): Option[ConnectInfo] = connectInfos().find(_.host == host)

  def find(): List[ConnectInfo] = connectInfos()

  def conf(): String = FileUtil.readAll(Directory.AppConf)

  def connectInfos(): List[ConnectInfo] = parseNamedConnectInfosJson(conf()).root.filter(_.disable == None)

  val default: NamedConnectInfos = NamedConnectInfos(List(ConnectInfo("localhost", "admin", "pass"), ConnectInfo("localhost2", "admin2", "pass2")))

  def init(file: File): Unit = {
    if (!file.exists()) {
      file.createNewFile() match {
        case true => // create new file success!
        case false => throw new RuntimeException(s"create new file fail! ${file.getAbsolutePath}")
      }
    }
    FileUtil.write(file, JsonUtil.toJsonString(ConfigUtil.default))
  }
}
