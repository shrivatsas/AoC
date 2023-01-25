import java.io.File

fun countContainerCombinations(containerSizes: List<Int>, targetVolume: Int): Int {
    val n = containerSizes.size
    val dp = Array(n + 1) { IntArray(targetVolume + 1) }

    // Initialize the base case
    for (i in 0..n) {
        dp[i][0] = 1
    }

    for (c in 1..n) {
        for (vol in 1..targetVolume) {
            if (containerSizes[c - 1] > vol) {
                dp[c][vol] = dp[c - 1][vol]
            } else {
                dp[c][vol] = dp[c - 1][vol] + dp[c - 1][vol - containerSizes[c - 1]]
            }
        }
    }
    return dp[n][targetVolume]
}

fun main() {
  val containerSizes = File("../17.input").readLines().map { it.toInt() }
  val targetVolume = 150
  val count = countContainerCombinations(containerSizes, targetVolume)
  println(count)
}