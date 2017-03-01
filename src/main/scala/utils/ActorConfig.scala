package utils

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._

trait ActorConfig {

  implicit val thorn = ActorSystem("thorn")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = thorn.dispatcher

  implicit val timeout: Timeout = 5.seconds
}
