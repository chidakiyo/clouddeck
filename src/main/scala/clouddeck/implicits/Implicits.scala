package clouddeck.implicits

import clouddeck.util.VMX

object Implicits {

  implicit class RichVMX(vmx: VMX) {
    import spray.json._
    import DefaultJsonProtocol._

    def toJson(): JsValue = {
      import clouddeck.util.VMXJsonProtocol._
      vmx.toJson
    }
  }

}
