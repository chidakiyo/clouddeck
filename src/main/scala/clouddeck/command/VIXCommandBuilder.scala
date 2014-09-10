package clouddeck.command

import clouddeck.command.Commands._
import clouddeck.Keys.Const._

object VIXCommandBuilder {

  /** guest os power on */
  def guestPowerOn(path: String)(implicit info: ConnectInfo): String = {
    s"${VIX_CMD.cmdAndOpt_vix(info)} start ${quot(path)}"
  }

  /** guest os power off */
  def guestPowerOff(path: String)(implicit info: ConnectInfo): String = {
    s"${VIX_CMD.cmdAndOpt_vix(info)} stop ${quot(path)}"
  }

}
