package actors

import akka.actor.Actor
import models.ModelMessage.Message
import utils.GetMessages


class ActorMessage extends Actor{

  def receive = {
    case GetMessages => sender() ! "No messages"
  }


}
