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

    "isOn return true when ON" in {
      val line = "getstate() = on"
      val status = Response.isOn(line)
      status must beTrue
    }

    "isOn return true when OFF" in {
      val line = "getstate() = off"
      val status = Response.isOn(line)
      status must beFalse
    }

  }

}
