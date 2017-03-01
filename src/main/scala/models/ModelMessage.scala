package models

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

object ModelMessage {

  final case class Message(id: Option[Long], user_id: Long, room_id: Long, message: String, timestamp: Long)
  implicit val messageFormat: RootJsonFormat[Message] = jsonFormat5(Message)
  final case class Messages(messages: List[Message])
  implicit val messagesFormat: RootJsonFormat[Messages] = jsonFormat1(Messages)
}
