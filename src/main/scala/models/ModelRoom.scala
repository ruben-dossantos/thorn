package models

import slick.driver.PostgresDriver.api._
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat
import utils.RoomHelper

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object ModelRoom extends BaseDAO with RoomHelper{
  final case class Room(id: Option[Long], name: String, lat: Double, long: Double, private_room: Boolean, description: Option[String])
  implicit val roomFormat: RootJsonFormat[Room] = jsonFormat6(Room)

  class RoomTable(tag: Tag) extends Table[Room](tag, "rooms"){
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def lat = column[Double]("lat")
    def long = column[Double]("long")
    def private_room = column[Boolean]("private_room")
    def description = column[Option[String]]("description")

    def * = (id.?,name,lat,long,private_room,description) <> ((Room.apply _).tupled, Room.unapply)
  }


  def getRooms(): Future[Seq[Room]] = rooms.result
  def getRoomsNear(lat: Double, long: Double) : Future[Seq[Room]] = {
    rooms
      .filter(_.lat < lat + 0.15)
      .filter(_.lat > lat - 0.15)
      .filter(_.long < long + 0.15)
      .filter(_.long > long - 0.15 )
      .result
  }
  def create(room: Room): Future[Long] = {
    if(createAllowed(room)) rooms returning rooms.map(_.id) += room
    else Future(0L)
  }
  def getRoomById(id: Long): Future[Room] = rooms.filter(_.id===id).result.head
  def delete(id: Long):Future[Int] = rooms.filter(_.id===id).delete


  def createAllowed(room: Room): Boolean = {
    val all_rooms = Await.result(getRoomsNear(room.lat, room.long).mapTo[Seq[Room]], 5000 millis)
    all_rooms foreach { a_room =>
      val percentage = percenteMatch(a_room.name, room.name)
      val distance = coordinatesDistance(a_room.lat, room.lat, a_room.long, room.long)
      if( percentage > 80 & distance < 10000) return false
    }
    true
  }
}
