package actors

import akka.actor.Actor
import models.ModelUser.{User, Users}
import utils.{GetUsers, SaveUser}

class ActorUser extends Actor {

  def receive = {
    case GetUsers => sender() ! Users(users)
    case save_user: SaveUser => users :::= List(save_user.user)
    case _  => println("da fuck")
  }


  var users: List[User] = List(
    User(Some(1), "rica", "white", 125.14, 154.2),
    User(Some(2), "l33t", "black", 111.11, 18.2),
    User(Some(3), "mad", "orange", 15.14, 214.2)
  )

}
