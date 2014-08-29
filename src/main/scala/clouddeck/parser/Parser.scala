package clouddeck.parser

import clouddeck.util.Response

object Parser {

  def images(list: String): List[String] = {
    list.lines.filter(_.length > 0).map(m => Response.toVMX(m.trim).name).toList
  }

}