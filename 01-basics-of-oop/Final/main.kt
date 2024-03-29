import java.util.UUID;

val lagos = Pair(6.465422, 3.406448)
val london = Pair(51.509865, -0.118092)

// Procedural programming function
// DONE: Add more inputs
fun computeTravelTime(from: Pair<Double, Double>,
                      to: Pair<Double, Double>,
                      averageSpeed: Double,
                      actualDistance: Double): Double {
  // compute point-to-point distance
  // assume some average driving speed?
  return actualDistance/averageSpeed
}


// Object-oriented programming classes
open class TravelMode(private val mode: String, val averageSpeed: Double) {
  val id = UUID.randomUUID().toString()

  open fun actualDistance(from: Pair<Double, Double>, to: Pair<Double, Double>): Double {
    // use relevant map info for each specific travel mode
    throw IllegalArgumentException("Implement this method for ${mode}.")
  }

  fun computeTravelTime(from: Pair<Double, Double>, to: Pair<Double, Double>): Double {
    return actualDistance(from, to)/averageSpeed
  }
}


// DONE: Add Walking subclass
class Walking(mode: String, averageSpeed: Double): TravelMode(mode, averageSpeed) {
  override fun actualDistance(from: Pair<Double, Double>, to: Pair<Double, Double>): Double {
    // use map info about walking paths, low-traffic roads, hills
    return 57.0
  }
}


// DONE: Add Driving subclass
class Driving(mode: String, averageSpeed: Double): TravelMode(mode, averageSpeed) {
  override fun actualDistance(from: Pair<Double, Double>, to: Pair<Double, Double>): Double {
    // use map info about roads, tollways
    return 57.0
  }

  // DONE: Over load computeTravelTime
  fun computeTravelTime(from: Pair<Double, Double>,
                        to: Pair<Double, Double>,
                        traffic: Double,
                        parking: Double): Double {
    return actualDistance(from, to)/averageSpeed * traffic + parking
  }
}


fun main() {
  //computeTravelTime()

  // DONE: Instantiate TravelMode
  val sammy = TravelMode(mode = "walking", averageSpeed = 4.5)
  println(sammy.mode)
//     sammy.actualDistance(Pair(12.4563, 32.2434), Pair(23.4424, 14.34413))

  // DONE: Instantiate Walking
  val tim = Walking(mode = "walking", averageSpeed = 6.0)
  println(tim)
  println(tim.actualDistance(from = Pair(12.4563, 32.2434), to = Pair(23.4424, 14.34413)))
  println(tim.computeTravelTime(from = Pair(12.4563, 32.2434), to = Pair(23.4424, 14.34413)))

  // DONE: Instantiate Driving
  val car = Driving(mode = "driving", averageSpeed = 50.0)
  println(car)
  val hours = car.computeTravelTime(from = lagos, to = london)
  println("Hours "+ hours)
  val realHours = car.computeTravelTime(
    from = lagos,
    to = london,
    traffic = 1.2,
    parking = 0.5
  )
  println("Actual Hours: " + realHours)
}
