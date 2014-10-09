package clouddeck.util

import spray.json._
import DefaultJsonProtocol._

case class Host(name: String, nickname: Option[String] = None, description: Option[String] = None, dataStorages: List[DataStorage] = Nil)
case class DataStorage(name: String, capacity: Long, freeSpace: Long)

object HostJsonProtocol extends DefaultJsonProtocol {
  implicit val dataFormat = jsonFormat3(DataStorage)
  implicit val vmxFormat = jsonFormat4(Host)
}
