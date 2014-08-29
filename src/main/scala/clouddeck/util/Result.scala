package clouddeck.util

import clouddeck.command.ConnectInfo
import spray.json._
import DefaultJsonProtocol._

case class Result(success: Option[Success] = None, error: Option[Error] = None)
case class Success(data: List[ConnectInfo])
case class Error(message: String, code: String)

object ResultJsonProtocol extends DefaultJsonProtocol {
  import MyJsonProtocol._
  implicit val successFormat = jsonFormat1(Success)
  implicit val errorFormat = jsonFormat2(Error)
  implicit val resultFormat = jsonFormat2(Result)
}
