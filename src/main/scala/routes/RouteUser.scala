package routes

import akka.actor.{ActorRef, Props}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.{StatusCodes, _}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask

import scala.concurrent.Future
import models.ModelUser.User
import utils.{ActorConfig, SaveUser}
import actors.ActorUser
import models.ModelUser
import spray.json.DefaultJsonProtocol._
import spray.json._

object RouteUser extends ActorConfig {

  val routes: Route =
    path("users"){
      get {
        complete(ModelUser.getUsers().map(_.toJson))
      } ~
        post { entity(as[User]) { user =>
          complete(ModelUser.create(user).map(_.toJson))
        }
        }
    } ~
      pathPrefix("users" / LongNumber) { id =>
        get{
          complete(ModelUser.getUserById(id).map(_.toJson))
        } ~
          put{
            complete("updated")
          } ~
          delete {
            complete(ModelUser.delete(id).map(_.toJson))
          }
      }
}
