package clouddeck.util

import ResultJsonProtocol.resultFormat
import clouddeck.command.VIJavaCommandBuilder
import spray.json.pimpAny
import clouddeck.command.VIXCommandBuilder

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
        val guests = VIJavaCommandBuilder.guestList()(info)
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
        val guests = VIJavaCommandBuilder.guestList()(info)
        guests.find(_.name == guestId) match {
          case Some(v) =>
            VIJavaCommandBuilder.guestState(guestId)(info) match {
              case Some(vmx) => Result[VMX](success = Some(Success(List(vmx)))).toJson.prettyPrint
              case None => Result[VMX](error = Some(Error(s"requested guest not found. ${guestId}", "E001"))).toJson.prettyPrint
            }
          case None => Result[VMX](error = Some(Error(s"requested guest not found. ${guestId}", "E001"))).toJson.prettyPrint
        }
      case None => Result[VMX](error = Some(Error(s"requested host not found. ${id}", "E001"))).toJson.prettyPrint
    }
  }

  def start(id: String, guestId: String): String = {
    import spray.json._
    import VMXJsonProtocol._
    import ResultJsonProtocol._
    ConfigUtil.connectInfos().find(_.host == id) match {
      case Some(info) =>
        val guests = VIJavaCommandBuilder.guestList()(info)
        guests.find(_.name == guestId) match {
          case Some(v) =>
            val cmd = VIXCommandBuilder.guestPowerOn(v.fullPath)(info)
            import scala.sys.process._
            val result = cmd.lines_!
            result match {
              case Stream() => Result[VMX](success = Some(Success(List(VMX(v.name, v.image, v.storage, v.fullPath))))).toJson.prettyPrint
              case e => Result[VMX](error = Some(Error(s"${e(0)}. ${guestId}", "E001"))).toJson.prettyPrint
            }
          case None => Result[VMX](error = Some(Error(s"requested guest not found. ${guestId}", "E001"))).toJson.prettyPrint
        }
      case None => Result[VMX](error = Some(Error(s"requested host not found. ${id}", "E001"))).toJson.prettyPrint
    }
  }

  def stop(id: String, guestId: String): String = {
    import spray.json._
    import VMXJsonProtocol._
    import ResultJsonProtocol._
    ConfigUtil.connectInfos().find(_.host == id) match {
      case Some(info) =>
        val guests = VIJavaCommandBuilder.guestList()(info)
        guests.find(_.name == guestId) match {
          case Some(v) =>
            val cmd = VIXCommandBuilder.guestPowerOff(v.fullPath)(info)
            import scala.sys.process._
            val result = cmd.lines_!
            result match {
              case Stream() => Result[VMX](success = Some(Success(List(VMX(v.name, v.image, v.storage, v.fullPath))))).toJson.prettyPrint
              case e => Result[VMX](error = Some(Error(s"${e(0)}. ${guestId}", "E001"))).toJson.prettyPrint
            }
          case None => Result[VMX](error = Some(Error(s"requested guest not found. ${guestId}", "E001"))).toJson.prettyPrint
        }
      case None => Result[VMX](error = Some(Error(s"requested host not found. ${id}", "E001"))).toJson.prettyPrint
    }
  }

}
