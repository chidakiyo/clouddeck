package clouddeck.util

import clouddeck.command.ConnectInfo
import spray.json._
import DefaultJsonProtocol._

case class Result[A](success: Option[Success[A]] = None, error: Option[Error] = None)
case class Success[A](data: List[A])
case class Error(message: String, code: String)

object ResultJsonProtocol extends DefaultJsonProtocol {
  implicit def successFormat[A: JsonFormat] = jsonFormat1(Success.apply[A])
  implicit val errorFormat = jsonFormat2(Error)
  implicit def resultFormat[A: JsonFormat] = jsonFormat2(Result.apply[A])
}
