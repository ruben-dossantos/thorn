package models

import slick.lifted.TableQuery
import scala.concurrent.Future
import slick.profile.{FixedSqlAction, SqlAction, FixedSqlStreamingAction}
import slick.dbio.{Effect, NoStream}

import models.ModelRoom.RoomTable
import utils.DatabaseConfig

/**
  * Created by ruben on 17-02-2017.
  */
trait BaseDAO extends DatabaseConfig {
  val rooms = TableQuery[RoomTable]

  protected implicit def executeFromDb[A](action: SqlAction[A, NoStream, _ <: slick.dbio.Effect]): Future[A] = {
    db.run(action)
  }
  protected implicit def executeReadStreamFromDb[A](action: FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]): Future[Seq[A]] = {
    db.run(action)
  }
}
