package clouddeck

import clouddeck.command.ConnectInfo

object Keys {

  object Command {
    val VmwareCmd = "vmware-cmd"
    val VixCmd = "vmrun"
    val VicfgHostops = "vicfg-hostops"
    val Vifs = "vifs"
    val Esxcli = "esxcli"
  }

  object Option {
    val user = (user: String) => s" --username ${user} "
    val pass = (pass: String) => s" --password ${pass} "
    val host = (host: String) => s" --server ${host} "
    val user_vix = (user: String) => s" -u ${user} "
    val pass_vix = (pass: String) => s" -p ${pass} "
    val host_vix = (host: String) => s" -T esx -h https://${host}/sdk "
    val basicOption = (info: ConnectInfo) => s"${host(info.host)}${user(info.user)}${pass(info.pass)}"
    val basicOptionVIX = (info: ConnectInfo) => s"${host_vix(info.host)}${user_vix(info.user)}${pass_vix(info.pass)}"
  }

  object Const {
    val dq = """""""
    val quot = (word: String) => s"$dq$word$dq"
    val contentroot = "./src/main/webapp/dist/"
  }

}