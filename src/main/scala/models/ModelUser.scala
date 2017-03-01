package models

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

object ModelUser {
  final case class User(id: Option[Long], username: String, color: String, lat: Double, long: Double)
  implicit val userFormat: RootJsonFormat[User] = jsonFormat5(User)
  case class Users(users: List[User])
  implicit val usersFormat: RootJsonFormat[Users] = jsonFormat1(Users)
}
