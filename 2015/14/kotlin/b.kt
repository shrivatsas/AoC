import java.io.File
import java.io.BufferedReader

data class Reindeer(val name: String, val speed: Int, val flyTime: Int, val restTime: Int) {
  val cycleTime = flyTime + restTime
  var points = 0

  fun distance(time: Int): Int {
    val cycles = time / cycleTime
    val remainingTime = time % cycleTime
    return cycles * speed * flyTime + Math.min(remainingTime, flyTime) * speed
  }
}

fun main() {
  val lines: List<String> = File("../14.input").useLines { it.toList() }

  // Vixen can fly 8 km/s for 8 seconds, but then must rest for 53 seconds.
  val regex = Regex("""(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""")
  val reindeers = lines.map { line ->
    val (name, speed, flyTime, restTime) = regex.matchEntire(line)!!.destructured
    Reindeer(name, speed.toInt(), flyTime.toInt(), restTime.toInt())
  }

  for (i in 1..2503) {
    val maxDistance = reindeers.map { it.distance(i) }.max()
    val leaders = reindeers.filter { it.distance(i) == maxDistance }
    leaders.forEach { it.points++ }
  }
  println(reindeers.map { it.points }.max())
}