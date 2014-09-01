package clouddeck.command

import clouddeck.command.Commands._
import clouddeck.Keys.Const._

object VMCommandBuilder {

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
    s"${VMWARE_CMD.cmdAndOpt(info)} ${quot(path)} getstate"
  }

  /** guest os power on */
  def guestPowerOn(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} ${quot(path)} start"
  }

  /** guest os power off (require VMWare Tools installed) */
  def guestPowerOff(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} ${quot(path)} stop soft"
  }

  /** guest os power off hard */
  def guestPowerOffHard(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} ${quot(path)} stop hard"
  }

  /** register to inventory */
  @deprecated("untested", "0.0.0")
  def guestRegisterInventory(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} -s register ${quot(path)}"
  }

  /** remove from inventory */
  @deprecated("untested", "0.0.0")
  def guestRemoveInventory(path: String)(implicit info: ConnectInfo): String = {
    s"${VMWARE_CMD.cmdAndOpt(info)} -s unregister ${quot(path)}"
  }

  // vifs

  /** Put file to Datastore */
  def fsPut(localPath: String, datastorePath: String)(implicit info: ConnectInfo): String = {
    s"${VIFS.cmdAndOpt(info)} -p ${quot(localPath)} ${quot(datastorePath)}"
  }

  /** Get file from Datastore */
  def fsGet(localPath: String, datastorePath: String)(implicit info: ConnectInfo): String = {
    s"${VIFS.cmdAndOpt(info)} -g ${quot(datastorePath)} ${quot(localPath)}"
  }

  /** Copy file DS1 to DS2 */
  def fsCopy(srcDSPath: String, dstDSPath: String)(implicit info: ConnectInfo): String = {
    s"${VIFS.cmdAndOpt(info)} -c ${quot(srcDSPath)} ${quot(dstDSPath)}"
  }

  /** Move file DS1 to DS2 */
  def fsMove(srcDSPath: String, dstDSPath: String)(implicit info: ConnectInfo): String = {
    s"${VIFS.cmdAndOpt(info)} -m ${quot(srcDSPath)} ${quot(dstDSPath)}"
  }

  /** Remove file */
  def fsRemove(datastorePath: String)(implicit info: ConnectInfo): String = {
    s"${VIFS.cmdAndOpt(info)} -r ${quot(datastorePath)}"
  }

  /** Show Datastore list */
  def fsList()(implicit info: ConnectInfo): String = {
    s"${VIFS.cmdAndOpt(info)} -S"
  }

  // esxcli

  /** find applied patch */
  def checkPatchList()(implicit info: ConnectInfo): String = {
    s"${ESXCLI.cmdAndOpt(info)} software vib list"
  }

  /** check patch info */
  def checkPatchInfo(patchPath: String)(implicit info: ConnectInfo): String = {
    s"${ESXCLI.cmdAndOpt(info)} software sources vib list -d ${quot(patchPath)}"
  }

  /** apply patch */
  def applyPatch(patchPath: String)(implicit info: ConnectInfo): String = {
    s"${ESXCLI.cmdAndOpt(info)} software vib update -d ${quot(patchPath)}"
  }

}