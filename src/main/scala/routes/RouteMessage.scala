package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import models.ModelMessage
import utils.ActorConfig
import spray.json._
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import models.ModelMessage.Message

object RouteMessage extends ActorConfig {

  val routes: Route =
    path("messages"){
      get{
        complete(ModelMessage.getMessages().map(_.toJson))
      } ~
        post { entity(as[Message]) { message =>
          complete(ModelMessage.create(message).map(_.toJson))
        }
        }
    } ~
      pathPrefix("messages" / LongNumber) { id =>
        get {
          complete(ModelMessage.getMessagesByRoomId(id).map(_.toJson))
        }
      }

}
