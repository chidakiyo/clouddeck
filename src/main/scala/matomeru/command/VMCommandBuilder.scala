package matomeru.command

object VMCommandBuilder {

  val dq = """""""

  def hostInfo()(implicit info: ConnectInfo): String = {
    s"vicfg-hostops --server ${info.host} --username ${info.user} --password ${info.pass} --operation info"
  }

  def guestList()(implicit info: ConnectInfo): String = {
    s"vmware-cmd --server ${info.host} --username ${info.user} --password ${info.pass} -l"
  }

  def guestState(line: String)(implicit info: ConnectInfo): String = {
    s"vmware-cmd --server ${info.host} --username ${info.user} --password ${info.pass} $dq$line$dq getstate"
  }
}