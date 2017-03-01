import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import scala.io.StdIn

import routes.{RouteRoom, RouteUser, RouteMessage}
import utils.ActorConfig

object Server extends ActorConfig{

  def main(args: Array[String]): Unit = {
    val routes = {
      RouteUser.routes ~ RouteRoom.routes ~ RouteMessage.routes ~ path(""){get{complete("THORN")}}
    }

    val binding = Http().bindAndHandle(routes, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\n Press RETURN to stop...")
    StdIn.readLine()
    binding
      .flatMap(_.unbind())
      .onComplete(_ => thorn.terminate())
  }

}
