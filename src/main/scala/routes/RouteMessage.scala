package routes

import akka.actor.{ActorRef, Props}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import actors.ActorMessage
import models.ModelMessage.Messages
import utils.{ActorConfig, GetMessages}
import akka.pattern.ask

import scala.concurrent.Future

object RouteMessage extends ActorConfig {

  val actorMessage: ActorRef = thorn.actorOf(Props[ActorMessage], "actorMessage")

  val routes: Route =
    path("messages"){
      get{
        val messages: Future[Messages] = (actorMessage ? GetMessages).mapTo[Messages]
        complete(messages)
      }
    }

}
