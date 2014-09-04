package clouddeck.command

import clouddeck.command.Commands._
import clouddeck.Keys.Const._

object VIXCommandBuilder {

  /** get guest os list */
  def guestList()(implicit info: ConnectInfo): String = {
    s"${VIX_CMD.cmdAndOpt_vix(info)} list"
  }

  /** guest os power on */
  def guestPowerOn(path: String)(implicit info: ConnectInfo): String = {
    s"${VIX_CMD.cmdAndOpt_vix(info)} start ${quot(path)}"
  }

  /** guest os power off */
  def guestPowerOff(path: String)(implicit info: ConnectInfo): String = {
    s"${VIX_CMD.cmdAndOpt_vix(info)} stop ${quot(path)}"
  }

  def main(args: Array[String]) {
    implicit val info = ConnectInfo("192.168.1.1", "root", "pass")
    val cmd = guestPowerOff("<path>.vmx")
    println(cmd)
    import scala.sys.process._
    val result = cmd.lines_!
    println(result)

  }

}
