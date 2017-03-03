package models

import models.ModelRoom.Room
import models.ModelUser.User
import slick.driver.PostgresDriver.api._
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

import scala.concurrent.{Await, Future}

object ModelMessage extends BaseDAO {

  final case class Message(id: Option[Long], user_id: Long, room_id: Long, message: String, timestamp: Long)
  implicit val messageFormat: RootJsonFormat[Message] = jsonFormat5(Message)

  class MessageTable(tag: Tag) extends Table[Message](tag, "messages") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def user_id = column[Long]("user_id")
    def room_id = column[Long]("room_id")
    def message = column[String]("message")
    def timestamp = column[Long]("timestamp")

    def * = (id.?, user_id, room_id, message, timestamp) <> ((Message.apply _).tupled, Message.unapply)
    def user = foreignKey("users", user_id, users)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
    def room = foreignKey("rooms", room_id, rooms)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
  }

  def getMessages(): Future[Seq[Message]] = messages.result
  def getMessagesByRoomId(room_id: Long): Future[Seq[Message]] = {

    messages.filter(_.room_id===room_id).result
  }
  def create(message: Message): Future[Long] = messages returning messages.map(_.id) += message


}
