package clouddeck.command

import clouddeck.Keys.Command._

/**
 *
 */
object Commands {

  val VMWARE_CMD = Command(VmwareCmd)
  val VIX_CMD = Command(VixCmd)
  val VICFG_HOSTOPS = Command(VicfgHostops)
  val VIFS = Command("vifs")
  val ESXCLI = Command("esxcli")

}
