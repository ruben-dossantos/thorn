package routes

import akka.actor.{ActorRef, Props}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.{StatusCodes, _}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask

import scala.concurrent.Future
import models.ModelUser.{User, Users}
import utils.{ActorConfig, GetUsers, SaveUser}
import actors.ActorUser

object RouteUser extends ActorConfig {

  val actorUser: ActorRef = thorn.actorOf(Props[ActorUser], "actorUser")

  val routes: Route =
    path("users"){
      get {
        val users: Future[Users] = (actorUser ? GetUsers).mapTo[Users]
        complete(users)
      } ~
        post { entity(as[User]){ user =>
            actorUser ! SaveUser(user)
            complete((StatusCodes.Accepted, "user created"))
          }
        }
    } ~
      pathPrefix("users" / LongNumber) { id =>
        get{
          complete(HttpEntity(ContentTypes.`application/json`, """{ "id": $id$, "name": "rui"}"""))
        } ~
          put{
            complete("updated")
          }
      }
}
