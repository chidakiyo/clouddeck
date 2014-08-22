package clouddeck.util

import java.lang.System._

sealed abstract class OS()
case class LinuxX86() extends OS()
case class LinuxX86_64() extends OS()
case class MacOsxX86_64() extends OS()
case class MacOsxX86() extends OS()
case class WindowsX86() extends OS()
case class WindowsX86_64() extends OS()
case class UnknownOS() extends OS()
object OS {
  val windows = """Windows.+""".r
  val linux = """linux.+""".r
  def apply(): OS =
    (getProperty("os.name"), getProperty("os.arch")) match {
      case (windows(), _) => WindowsX86()
      case (linux(), _) => LinuxX86()
      //      case (windows(), "amd64") => WindowsX86_64()
      case ("Mac OS X", "x86_64") => MacOsxX86_64()
      case ("Mac OS X", "x86") => MacOsxX86_64()
      case _ => UnknownOS()
    }
}

object OSUtil {
  def isWindows(): Boolean = {
    OS() match {
      case WindowsX86() | WindowsX86_64() => true
      case _ => false
    }
  }
}