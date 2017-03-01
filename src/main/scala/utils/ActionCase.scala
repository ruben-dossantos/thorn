package utils

import models.ModelMessage.Message
import models.ModelRoom.Room
import models.ModelUser.User
import models.ModelUserRoom.UserRoom


case class SaveUser(user: User)
case object GetUsers
case class GetUser(user_id: Long)

case class SaveRoom(room: Room)
case object GetRooms
case class GetRoom(room_id: Long)

case class SaveUserRoom(userRoom: UserRoom)
case object GetUsersRooms
case class GetUsersRoom(room_id: Long)
case class GetUserRooms(user_id: Long)

case class SaveMessage(message: Message)
case object GetMessages
case class GetUserMessages(user_id: Long)
case class GetRoomMessages(room_id: Long)



