package utils

import slick.driver.PostgresDriver.api._

trait DatabaseConfig {
  val db = Database.forConfig("thorndb")

  implicit val session: Session = db.createSession()
}
