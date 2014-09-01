package clouddeck.parser

import clouddeck.util.Response
import clouddeck.util.VMX

object Parser {

  def images(list: String): List[VMX] = {
    list.lines.filter(_.length > 0).map(m => Response.toVMX(m.trim)).toList
  }

}