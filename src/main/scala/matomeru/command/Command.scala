package matomeru.command

import scala.sys.process._

/**
 * Execute external command
 */
object Command {

  val doCommand = (com: String) => com.!!

}