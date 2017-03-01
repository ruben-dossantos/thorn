package models

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

object ModelUserRoom {

  final case class UserRoom(id: Option[Long], user_id: Long, room_id: Long)
  implicit val userRoomFormat: RootJsonFormat[UserRoom] = jsonFormat3(UserRoom)
  final case class UsersRoom(usersRoom: List[UserRoom])
  implicit val usersRoomFormat: RootJsonFormat[UsersRoom] = jsonFormat1(UsersRoom)
}
