package actors

import akka.actor.Actor
import models.ModelRoom
import models.ModelRoom.Room
import utils.{GetRoom, GetRooms, SaveRoom}

import scala.concurrent.ExecutionContext.Implicits.global

class ActorRoom extends Actor {

  import akka.pattern.pipe

  def receive = {
    case GetRooms => ModelRoom.getRooms().pipeTo(sender())
    case save_room: SaveRoom => rooms :::= List(save_room.room)
    case get_room: GetRoom => rooms foreach { room => if(room.id.get == get_room.room_id){ sender() ! room}}
    case _ => println(" no comprende")
  }

  var rooms: List[Room] = List(
    Room(Some(1), "desigual", 123.4, 564.4, private_room = false, None),
    Room(Some(2), "ferrara plaza", 103.4, 514.4, private_room = false, None),
    Room(Some(3), "desigual_pvt", 123.4, 564.4, private_room = true, None)
  )

}
