package clouddeck.util

import spray.json._
import DefaultJsonProtocol._
import clouddeck.command.ConnectInfo

case class NamedList[A](root: List[A])

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val connectInfoFormat = jsonFormat3(ConnectInfo)
  implicit def namedListFormat[A: JsonFormat] = jsonFormat1(NamedList.apply[A])
}

object JsonUtil {

  import MyJsonProtocol._

  def toJsonString(x: ConnectInfo): String = x.toJson.prettyPrint
  def toJsonString(x: NamedList[ConnectInfo]): String = x.toJson.prettyPrint
  def toJsonString(x: List[ConnectInfo]): String = x.toJson.prettyPrint

  def parseConnectInfoJson(x: String): ConnectInfo = x.parseJson.convertTo[ConnectInfo]
  def parseConnectInfosJson(x: String): List[ConnectInfo] = x.parseJson.convertTo[List[ConnectInfo]]
  def parseNamedConnectInfosJson(x: String): NamedList[ConnectInfo] = x.parseJson.convertTo[NamedList[ConnectInfo]]
}
