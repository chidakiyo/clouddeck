package clouddeck.util

import org.specs2.mutable.Specification

class ResponseSpec extends Specification {

  "Response" should {

    "toVMX" in {
      val line = "/vmfs/volumes/abcdef-ghijkl-mnop-0123456789/VMX TEST/BASE VMX.vmx"
      val vmx = Response.toVMX(line)
      println(vmx)
      vmx.fullPath mustEqual line
      vmx.name mustEqual "VMX TEST"
      vmx.image mustEqual "BASE VMX.vmx"
      vmx.storage mustEqual "abcdef-ghijkl-mnop-0123456789"
    }

  }

}
