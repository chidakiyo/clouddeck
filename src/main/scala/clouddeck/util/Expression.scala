package clouddeck.util

import clouddeck.command.ConnectInfo
import clouddeck.command.Commands
import clouddeck.parser.Parser
import clouddeck.command.VMCommandBuilder

object Expression {

  def hosts(): String = {
    import spray.json._
    import HostJsonProtocol._
    import ResultJsonProtocol._
    val hosts = ConfigUtil.connectInfos.map(c => Host(c.host))
    Result[Host](success = Some(Success(hosts))).toJson.prettyPrint
  }

  def guests(id: String): String = {
    import spray.json._
    import VMXJsonProtocol._
    import ResultJsonProtocol._

    ConfigUtil.connectInfos().find(_.host == id) match {
      case Some(info) =>
        val cmd = VMCommandBuilder.guestList()(info)

        import scala.sys.process._
        val guests = Parser.images(cmd.!!)
        Result[VMX](success = Some(Success(guests))).toJson.prettyPrint
      case None => Result[VMX](error = Some(Error(s"requested host not found. ${id}", "E001"))).toJson.prettyPrint
    }
  }

  def state(id: String, guestId: String): String = {
    import spray.json._
    import VMXJsonProtocol._
    import ResultJsonProtocol._

    ConfigUtil.connectInfos().find(_.host == id) match {
      case Some(info) =>

        val cmd = VMCommandBuilder.guestList()(info)

        import scala.sys.process._
        val guests = Parser.images(cmd.!!)

        guests.find(_.name == guestId) match {
          case Some(v) =>
            val cmd = VMCommandBuilder.guestState(v.fullPath)(info)
            import scala.sys.process._
            val result = Response.isOn(cmd.!!)
            val vmx = VMX(v.name, v.image, v.storage, v.fullPath, Some(result))
            Result[VMX](success = Some(Success(List(vmx)))).toJson.prettyPrint
          case None => Result[VMX](error = Some(Error(s"requested guest not found. ${guestId}", "E001"))).toJson.prettyPrint
        }

      case None => Result[VMX](error = Some(Error(s"requested host not found. ${id}", "E001"))).toJson.prettyPrint
    }
  }

}
