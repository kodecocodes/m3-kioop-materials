import java.util.UUID;

// Procedural programming function
// TODO: Add more inputs
fun computeTravelTime(): Double {
    // compute point-to-point distance
  // assume some average driving speed?
    return 42.0
}


// Object-oriented programming classes
class TravelMode(val mode: String, val averageSpeed: Double) {
  val id = UUID.randomUUID().toString()

  fun actualDistance(from: Double, to: Double): Double {
    // use relevant map info for each specific travel mode
    throw IllegalArgumentException("Implement this method for ${mode}.")
  }

  fun computeTravelTime(distance: Double): Double {
    return distance/averageSpeed
  }
}

// TODO: Instantiate TravelMode


// TODO: Add Walking subclass


// TODO: Instantiate Walking


// TODO: Add Driving subclass

// TODO: Over load computeTravelTime

// TODO: Instantiate Driving

fun main() {
    //computeTravelTime()
}