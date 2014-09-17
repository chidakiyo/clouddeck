package clouddeck.util

sealed abstract class VMwareToolsStatus {
  override def toString(): String = {
    getClass().getSimpleName()
  }
}
case class Installed() extends VMwareToolsStatus
case class NotInstalled() extends VMwareToolsStatus
case class ToBeUpdated() extends VMwareToolsStatus
case class UnKnownStatus() extends VMwareToolsStatus

object VMwareTools {

  def apply(name: String): VMwareToolsStatus = {
    name match {
      case "toolsNotInstalled" => NotInstalled()
      case "toolsOld" => ToBeUpdated()
      case "toolsOk" => Installed()
      case _ => UnKnownStatus()
    }
  }
}

object VMwareToolsUtil {

  def isInstalled(status: VMwareToolsStatus): Boolean = {
    status match {
      case Installed() | ToBeUpdated() => true
      case _ => false
    }
  }

  def toBeUpdated(status: VMwareToolsStatus): Boolean = {
    status match {
      case ToBeUpdated() => true
      case _ => false
    }
  }
}
