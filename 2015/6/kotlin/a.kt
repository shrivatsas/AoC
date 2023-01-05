import java.util.regex.Pattern
import java.io.File

data class Coordinate(val x: Int, val y: Int)

val p = Pattern.compile("(turn off|turn on|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)")
val lights = Array(1000) { Array(1000) { false } }

val turn = { c1: Coordinate, c2: Coordinate, s: Boolean -> 
                for (i in c1.x..c2.x) {
                  for(j in c1.y..c2.y) {
                    lights[i][j] = s
                  }
                }
            }

val toggle = { c1: Coordinate, c2: Coordinate -> 
                for (i in c1.x..c2.x) {
                  for (j in c1.y..c2.y) {
                    lights[i][j] = !lights[i][j]   
                  }
                }
            }

fun main() {
  val file = File("../6.input")
  file.forEachLine { line ->
    val matcher = p.matcher(line)
    if (matcher.find()) {
      val action = matcher.group(1)
      val start = Coordinate(matcher.group(2).toInt(), matcher.group(3).toInt())
      val end = Coordinate(matcher.group(4).toInt(), matcher.group(5).toInt())

      when (action) {
        "turn on" -> turn(start, end, true)
        "turn off" -> turn(start, end, false)
        "toggle" -> toggle(start, end)
      } 
    }
  }

  println(lights.map { it.count { it } }.sum())
  println(lights.flatten().count { it })
}