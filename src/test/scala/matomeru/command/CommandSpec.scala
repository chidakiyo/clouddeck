package matomeru.command

import org.specs2.mutable.Specification

class CommandSpec extends Specification {

  "Command" should {

    "Execute external command" in {
      val result = Command.doCommand("ls")
      result must contain("build.sbt")
    }

  }

}