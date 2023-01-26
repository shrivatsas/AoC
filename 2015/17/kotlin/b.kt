import java.io.File

fun containerCombination(size: Int, containerSizes: List<Int>, sid: Int, tdata: MutableList<Int>, tid: Int): Int {
  if (tid == size) {
    if (tdata.sum() == 150) {
      return 1
    }
    return 0
  }

  if (sid == containerSizes.size) return 0

  tdata[tid] = containerSizes[sid]
  return containerCombination(size, containerSizes, sid + 1, tdata, tid + 1) + containerCombination(size, containerSizes, sid + 1, tdata, tid)
}

fun main() {
  val containerSizes = File("../17.input").readLines().map { it.toInt() }
  val targetVolume = 150

  for (i in 1..containerSizes.size) {
    val combos = containerCombination(i, containerSizes, 0, MutableList<Int>(i) { 0 }, 0)
    if (combos > 0) {
      println("Number of $i combinations: $combos")
    }
  }
}
