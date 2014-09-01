package clouddeck

import clouddeck.command.ConnectInfo

object Keys {

  object Command {
    val VmwareCmd = "vmware-cmd"
    val VicfgHostops = "vicfg-hostops"
  }

  object Option {
    val user = (user: String) => s" --username ${user} "
    val pass = (pass: String) => s" --password ${pass} "
    val host = (host: String) => s" --server ${host} "
    val basicOption = (info: ConnectInfo) => s"${host(info.host)}${user(info.user)}${pass(info.pass)}"
  }

  object Const {
    val dq = """""""
    val quot = (word: String) => s"$dq$word$dq"
  }

}