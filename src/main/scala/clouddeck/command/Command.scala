package clouddeck.command

import clouddeck.util.OSUtil
import clouddeck.Keys.Option._

/**
 *
 */
class Command(cmd: String) {
  val isWindows = OSUtil.isWindows()

  def cmdStr() = {
    isWindows match {
      case true => s"cmd.exe /c ${cmd}.pl"
      case false => cmd
    }
  }

  def cmdAndOpt(info: ConnectInfo): String = {
    this.cmdStr + basicOption(info)
  }
}

object Command {
  def apply(cmd: String): Command = new Command(cmd)
}

