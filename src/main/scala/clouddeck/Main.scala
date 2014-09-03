package clouddeck

import java.net.URL
import com.vmware.vim25._
import com.vmware.vim25.mo._

object Main extends App {

  val si = new ServiceInstance(new URL("https://192.168.1.1/sdk"), "root", "pass", true)
  val rootFolder = si.getRootFolder()
  val mes = rootFolder.getChildEntity();

  val a = mes.toList.filter(_.isInstanceOf[Datacenter]).map(_.asInstanceOf[Datacenter])

  val dc = mes(0).asInstanceOf[Datacenter]
  val vmFolder = dc.getVmFolder()
  val vms = vmFolder.getChildEntity()

  val vm = vms.toList.filter(_.isInstanceOf[VirtualMachine]).map(_.asInstanceOf[VirtualMachine]).find(_.getName == "hoge")

  vm.map { v =>
    println(v.getName)
    val vmri = v.getRuntime().asInstanceOf[VirtualMachineRuntimeInfo]
    println(vmri.getPowerState())
    if (vmri.getPowerState() == VirtualMachinePowerState.poweredOn) {
      val task = v.powerOffVM_Task()
      task.waitForMe()
      println("vm:" + v.getName() + " powered off.")
    }
  }

  //  val vm = mes(0).asInstanceOf[VirtualMachine]
  //
  //  val vminfo = vm.getConfig();
  //  val vmc = vm.getCapability();
  //
  //  vm.getResourcePool()
  //  println("Hello " + vm.getName())
  //  println("GuestOS: " + vminfo.getGuestFullName())
  //  println("Multiple snapshot supported: " + vmc.isMultipleSnapshotsSupported())

  si.getServerConnection().logout()
}
