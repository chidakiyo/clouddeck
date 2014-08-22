package clouddeck.command

import org.specs2.mutable.Specification
import clouddeck.command.VMCommandBuilder._

class VMCommandBuilderSpec extends Specification {

  "VMCommandBuilder" should {

    implicit val info = ConnectInfo("host", "user", "pass")

    "build hostInfo command" in {
      hostInfo() mustEqual ("vicfg-hostops --server host --username user --password pass --operation info")
    }

    "build guestList command" in {
      guestList() mustEqual ("vmware-cmd --server host --username user --password pass -l")
    }

    "build guestState command" in {
      guestState("state") mustEqual ("vmware-cmd --server host --username user --password pass \"state\" getstate")
    }
  }

}