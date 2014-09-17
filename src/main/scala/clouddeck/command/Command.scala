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
      // windows
      case true => s"cmd.exe /c ${cmd}.pl"
      // other os (Linux/MacOS)
      case false => cmd
    }
  }

  def cmdAndOpt(info: ConnectInfo): String = {
    this.cmdStr + basicOption(info)
  }

  def cmdAndOpt_vix(info: ConnectInfo): String = {
    s"cmd.exe /c ${cmd}.exe" + basicOptionVIX(info)
  }
}

object Command {
  def apply(cmd: String): Command = new Command(cmd)
}

