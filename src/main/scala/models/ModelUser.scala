package models

import slick.driver.PostgresDriver.api._
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.{Await, Future}

object ModelUser extends BaseDAO {
  final case class User(id: Option[Long], name: String, username: String, hashedPassword: String, color: String, lat: Double, long: Double)
  implicit val userFormat: RootJsonFormat[User] = jsonFormat7(User)

  class UserTable(tag: Tag) extends Table[User](tag, "users"){
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def username = column[String]("username")
    def hashedPassword = column[String]("hashedpassword")
    def color = column[String]("color")
    def lat = column[Double]("lat")
    def long = column[Double]("long")

    def * = (id.?, name, username, hashedPassword, color, lat, long) <> ((User.apply _).tupled, User.unapply)
  }

  def getUsers(): Future[Seq[User]] = users.result
  def create(user: User) :Future[Long] = users returning users.map(_.id) += user
  def getUserById(id: Long) : Future[User] = users.filter(_.id===id).result.head
  def delete(id: Long) :Future[Int] = users.filter(_.id===id).delete
}
