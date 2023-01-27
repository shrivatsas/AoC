import java.io.File

fun isOn(d: List<CharArray>, i: Int, j: Int): Boolean {
  if (i < 0 || i > d.size - 1) {
    return false
  }
  if (j < 0 || j > d[0].size - 1) {
    return false
  }
  return d[i][j] == '#'
}

fun neighbourCount(d: List<CharArray>, i: Int, j: Int): Pair<Int, Int> {
  var neighbours = MutableList<Boolean>(8) { false }
  neighbours[0] = isOn(d, i-1, j-1)
  neighbours[1] = isOn(d, i, j-1)
  neighbours[2] = isOn(d, i+1, j-1)
  neighbours[3] = isOn(d, i+1, j)
  neighbours[4] = isOn(d, i+1, j+1)
  neighbours[5] = isOn(d, i, j+1)
  neighbours[6] = isOn(d, i-1, j+1)
  neighbours[7] = isOn(d, i-1, j)
  return Pair<Int, Int>(neighbours.count({ it }), neighbours.count({ !it }))
}

fun nextState(disp: List<CharArray>): List<CharArray> {
  val d = MutableList<CharArray>(disp.size) { CharArray(disp.size) }

  for (i in disp.indices) {
    for (j in disp[0].indices) {

    // A # means "on", and a . means "off".
    // A light which is on stays on when 2 or 3 neighbors are on, and turns off otherwise.
    // A light which is off turns on if exactly 3 neighbors are on, and stays off otherwise.
      val (on, off) = neighbourCount(disp, i, j)
      val c = disp[i][j]
      if (c == '#') {
        if (on == 2 || on == 3) {
          d[i][j] = '#'
        } else {
          d[i][j] = '.'
        }
      } else {
        if (on == 3) {
          d[i][j] = '#'
        } else {
          d[i][j] = '.'
        }
      }
    }
  }
  return d
}

fun main() {
  var d: List<CharArray> = File("../18.input").readLines().map({ it.toCharArray() })
  val count = 100
  for (i in 1..count) {
    d = nextState(d)
    if (i == count) {
      println("----")
      d.forEach { println(it) }
      println(d.map({ it.count({ it == '#' }) }).sum())
    }
  }
}