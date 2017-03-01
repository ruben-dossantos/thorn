package actors

import akka.actor.Actor
import models.ModelMessage.{Message, Messages}
import utils.GetMessages


class ActorMessage extends Actor{

  def receive = {
    case GetMessages => sender() ! Messages(messages)
  }


  val messages: List[Message] = List(
    Message(Some(1),1,1,"ola", 1487260796182L)
  )
}
