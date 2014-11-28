package clouddeck.command

case class ConnectInfo(host: String, user: String, pass: String, nickname: Option[String] = None, description: Option[String] = None, disable: Option[String] = None)
