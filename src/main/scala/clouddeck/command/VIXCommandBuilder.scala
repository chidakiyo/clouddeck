package clouddeck.command

import clouddeck.command.Commands._
import clouddeck.Keys.Const._
import java.net.InetAddress

object VIXCommandBuilder {

  /** guest os power on */
  def guestPowerOn(path: String)(implicit info: ConnectInfo): String = {
    s"${VIX_CMD.cmdAndOpt_vix(info)} start ${quot(path)}"
  }

  /** guest os power off */
  def guestPowerOff(path: String)(implicit info: ConnectInfo): String = {
    s"${VIX_CMD.cmdAndOpt_vix(info)} stop ${quot(path)}"
  }

  /** run command in guest os */
  def runInGuest(path: String, command: String)(user: String, pass: String)(implicit info: ConnectInfo): String = {
    s"${VIX_CMD.cmdAndOpt_vix(info)} -gu ${user} -gp ${pass} runProgramInGuest ${quot(path)} ${command}"
  }

  val CURL_PATH = "/usr/bin/curl"
  val localAddress = InetAddress.getLocalHost().getHostAddress()

  def curl(params: Map[String, String]): String = {
    import clouddeck.AppConfig
    val param = params.foldLeft("") { (s: String, pair: (String, String)) => s + " -d " + pair._1 + "=" + pair._2.replaceAll("\\s", "%20") }
    s"${CURL_PATH} http://${localAddress}:${AppConfig.PORT}/ -X POST ${param}"
  }

}
