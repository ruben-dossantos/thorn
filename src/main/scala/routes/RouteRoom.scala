package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Route
import models.ModelRoom.Room
import spray.json._
import models.ModelRoom
import spray.json.DefaultJsonProtocol._

import scala.concurrent.ExecutionContext.Implicits.global

object RouteRoom {

  val routes: Route =
    path("rooms"){
      get {
        parameters('lat.as[Double].?, 'long.as[Double].?) { (lat, long) =>
          if(lat.isDefined & long.isDefined){
            complete(ModelRoom.getRoomsNear(lat.get, long.get).map(_.toJson))
          } else {
            complete(ModelRoom.getRooms().map(_.toJson))
          }
        }

      } ~
        post { entity(as[Room]){ room =>
          complete(ModelRoom.create(room).map(_.toJson))
        }
        }
    } ~
      pathPrefix("rooms" / LongNumber) { id =>
        get {
          complete(ModelRoom.getRoomById(id).map(_.toJson))
        } ~
          put {
            complete("updated")
          } ~
          delete {
            complete(ModelRoom.delete(id).map(_.toJson))
          }
      }

}
