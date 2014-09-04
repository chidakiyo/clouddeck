package clouddeck.command

import clouddeck.util.VMX
import com.vmware.vim25.mo.ServiceInstance
import java.net.URL
import com.vmware.vim25.mo.Datacenter
import com.vmware.vim25.mo.VirtualMachine
import com.vmware.vim25.VirtualMachineRuntimeInfo
import com.vmware.vim25.VirtualMachinePowerState

object VIJavaCommandBuilder {

  /** get guest os list */
  def guestList()(implicit info: ConnectInfo): List[VMX] = {
    base()
  }

  /** guest os power status */
  def guestState(name: String)(implicit info: ConnectInfo): Option[VMX] = {
    val vmxs = base()
    vmxs.find(_.name == name)
  }

  def base()(implicit info: ConnectInfo): List[VMX] = {
    val si = new ServiceInstance(new URL(s"https://${info.host}/sdk"), info.user, info.pass, true)
    val rootFolder = si.getRootFolder()
    val mes = rootFolder.getChildEntity();

    val dc = mes.toList.filter(_.isInstanceOf[Datacenter]).map(_.asInstanceOf[Datacenter])
    val vmFolder = dc(0).getVmFolder()
    val vms = vmFolder.getChildEntity()

    val vmxs = vms.toList.filter(_.isInstanceOf[VirtualMachine]).map(_.asInstanceOf[VirtualMachine]).map { v =>
      VMX(v.getName, "image", "storage", "fullPath", Some(v.getRuntime().asInstanceOf[VirtualMachineRuntimeInfo].getPowerState() == VirtualMachinePowerState.poweredOn))
    }
    si.getServerConnection().logout()
    vmxs
  }

}
