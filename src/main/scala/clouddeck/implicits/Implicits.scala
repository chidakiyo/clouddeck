package clouddeck.implicits

object Implicits {
  implicit class CommandString(val com: String) extends AnyVal {
    import scala.sys.process._
    def !!!(): String = com.!!
  }
}