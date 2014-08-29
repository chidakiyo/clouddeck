package clouddeck.command

import clouddeck.command.Commands._

object VMCommandBuilder {

  val dq = """""""
  val fs = "vifs"
  val es = "esxcli"

  // vicfg-hostops

  /** ESXi host information */
  def hostInfo()(implicit info: ConnectInfo): String = {
    s"${VICFG_HOSTOPS.cmdAndOpt(info)} --operation info"
  }

  /** maintenance mode start */
  def hostMaintenanceOn()(implicit info: ConnectInfo): String = {
    s"${VICFG_HOSTOPS.cmdAndOpt(info)} --operation enter"
  }

  /** maintenance mode end */
  def hostMaintenanceOff()(implicit info: ConnectInfo): String = {
    s"${VICFG_HOSTOPS.cmdAndOpt(info)} --operation exit"
  }

  /** ESXi host reboot */
  def hostReboot()(implicit info: ConnectInfo): String = {
    s"${VICFG_HOSTOPS.cmdAndOpt(info)} --operation reboot"
  }

  /** ESXi host shutdown */
  def hostShutdown()(implicit info: ConnectInfo): String = {
    s"${VICFG_HOSTOPS.cmdAndOpt(info)} --operation shutdown"
  }

  // vmware-cmd

  /** get guest os list */
  def guestList()(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} -l"
  }

  /** guest os power status */
  def guestState(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} $dq$path$dq getstate"
  }

  /** guest os power on */
  def guestPowerOn(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} $dq$path$dq start"
  }

  /** guest os power off (require VMWare Tools installed) */
  def guestPowerOff(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} $dq$path$dq stop soft"
  }

  /** guest os power off hard */
  def guestPowerOffHard(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} $dq$path$dq stop hard"
  }

  /** register to inventory */
  @deprecated("untested", "0.0.0")
  def guestRegisterInventory(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} -s register $dq$path$dq"
  }

  /** remove from inventory */
  @deprecated("untested", "0.0.0")
  def guestRemoveInventory(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} -s unregister $dq$path$dq"
  }

  // vifs

  /** Put file to Datastore */
  def fsPut(localPath: String, datastorePath: String)(implicit info: ConnectInfo): String = {
    s"${fsBase(info)} -p $dq$localPath$dq $dq$datastorePath$dq"
  }

  /** Get file from Datastore */
  def fsGet(localPath: String, datastorePath: String)(implicit info: ConnectInfo): String = {
    s"${fsBase(info)} -g $dq$datastorePath$dq $dq$localPath$dq"
  }

  /** Copy file DS1 to DS2 */
  def fsCopy(srcDSPath: String, dstDSPath: String)(implicit info: ConnectInfo): String = {
    s"${fsBase(info)} -c $dq$srcDSPath$dq $dq$dstDSPath$dq"
  }

  /** Move file DS1 to DS2 */
  def fsMove(srcDSPath: String, dstDSPath: String)(implicit info: ConnectInfo): String = {
    s"${fsBase(info)} -m $dq$srcDSPath$dq $dq$dstDSPath$dq"
  }

  /** Remove file */
  def fsRemove(datastorePath: String)(implicit info: ConnectInfo): String = {
    s"${fsBase(info)} -r $dq$datastorePath$dq"
  }

  /** Show Datastore list */
  def fsList()(implicit info: ConnectInfo): String = {
    s"${fsBase(info)} -S"
  }

  // esxcli

  /** find applied patch */
  def checkPatchList()(implicit info: ConnectInfo): String = {
    s"${esBase(info)} software vib list"
  }

  /** check patch info */
  def checkPatchInfo(patchPath: String)(implicit info: ConnectInfo): String = {
    s"${esBase(info)} software sources vib list -d $dq$patchPath$dq"
  }

  /** apply patch */
  def applyPatch(patchPath: String)(implicit info: ConnectInfo): String = {
    s"${esBase(info)} software vib update -d $dq$patchPath$dq"
  }

  // base

  private def fsBase(info: ConnectInfo): String = {
    s"$fs ${base(info)}"
  }

  private def esBase(info: ConnectInfo): String = {
    s"$es ${base(info)}"
  }

  private def base(info: ConnectInfo): String = {
    s"--server ${info.host} --username ${info.user} --password ${info.pass}"
  }
}