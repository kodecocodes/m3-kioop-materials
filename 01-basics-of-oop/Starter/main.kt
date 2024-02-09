import java.util.UUID;

val lagos = Pair(6.465422, 3.406448)
val london = Pair(51.509865, -0.118092)

// Procedural programming function
// TODO: Add more inputs
fun computeTravelTime(from: Pair<Double, Double>, to: Pair<Double, Double>): Double {
  // compute point-to-point distance
  // assume some average driving speed?
  return 42.0
}


// Object-oriented programming classes
class TravelMode(val mode: String, val averageSpeed: Double) {
  val id = UUID.randomUUID().toString()

  fun actualDistance(from: Pair<Double, Double>, to: Pair<Double, Double>): Double {
    // use relevant map info for each specific travel mode
    throw IllegalArgumentException("Implement this method for ${mode}.")
  }

  fun computeTravelTime(from: Pair<Double, Double>, to: Pair<Double, Double>): Double {
    return actualDistance(from, to)/averageSpeed
  }
}


// TODO: Add Walking subclass


// TODO: Add Driving subclass


// TODO: Over load computeTravelTime



fun main() {
  //computeTravelTime()

  // TODO: Instantiate TravelMode


  // TODO: Instantiate Walking


  // TODO: Instantiate Driving
}