package clouddeck.util

import spray.json._
import DefaultJsonProtocol._
import clouddeck.command.ConnectInfo

case class NamedConnectInfos(root: List[ConnectInfo])

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val connectInfoFormat = jsonFormat3(ConnectInfo)
  implicit val namedListFormat = jsonFormat1(NamedConnectInfos)
}

object JsonUtil {

  import MyJsonProtocol._

  def toJsonString(x: ConnectInfo): String = x.toJson.prettyPrint
  def toJsonString(x: NamedConnectInfos): String = x.toJson.prettyPrint
  def toJsonString(x: List[ConnectInfo]): String = x.toJson.prettyPrint

  def parseConnectInfoJson(x: String): ConnectInfo = x.parseJson.convertTo[ConnectInfo]
  def parseConnectInfosJson(x: String): List[ConnectInfo] = x.parseJson.convertTo[List[ConnectInfo]]
  def parseNamedConnectInfosJson(x: String): NamedConnectInfos = x.parseJson.convertTo[NamedConnectInfos]
}
