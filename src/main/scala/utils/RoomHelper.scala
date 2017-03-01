package utils

/**
  * Created by ruben on 01-03-2017.
  */
trait RoomHelper {

  def percenteMatch(s1: String, s2: String): Integer = {
    val n1 = s1.trim replaceAll("\\s+", " ")
    val n2 = s2.trim replaceAll("\\s+", " ")
    val x = LevenDistance(n1,n2) * 100.toFloat
    val y = s1.length.toFloat + s2.length
    (100 - (x / y)).toInt
  }

  def LevenDistance(s1: String, s2: String): Integer = {
    val len1 = s1.length + 1
    val len2 = s2.length + 1

    var cost = new Array[Int](len1)
    var newCost = new Array[Int](len1)

    for( i <- 0 until len1 ){
      cost(i) = i
    }

    for( j <- 1 until len2){
      newCost(0) = j - 1

      for( i <- 1 until len1){

        val this_match = if(s1.charAt( i - 1) == s2.charAt(j - 1)) 0 else 1

        val cost_replace = cost( i - 1 ) + this_match
        val cost_insert = cost(i) + 1
        val cost_delete = newCost(i - 1) + 1

        newCost(i) = Math.min(Math.min(cost_insert, cost_delete),cost_replace)
      }

      val swap: Array[Int] = cost
      cost = newCost
      newCost = swap
    }
    cost(len1 - 1)
  }

  def coordinatesDistance(lat1: Double, lat2: Double, lon1: Double, lon2: Double) : Double = {

    val r = 6371e3
    val delta_lat = Math.toRadians(lat2 - lat1)
    val delta_lon = Math.toRadians(lon2 - lon1)

    val a = Math.sin(delta_lat / 2) * Math.sin(delta_lat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(delta_lon / 2) * Math.sin(delta_lon / 2)
    val c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))

    r * c
  }

}
