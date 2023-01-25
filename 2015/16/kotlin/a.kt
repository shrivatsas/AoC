import java.io.File

fun main() {
  val lines: List<String> = File("../16.input").useLines { it.toList() }

  // Sue 410: trees: 1, akitas: 10, vizslas: 6
  val pattern = Regex("""(\w+): (\d+)""")
  val maps = lines.map { line ->
    val matches = pattern.findAll(line)
    val m: Map<String, Int> = matches.associate { match -> match.groupValues[1] to match.groupValues[2].toInt() }
    m + mapOf("Sue" to line.split(":")[0].split(" ")[1].toInt())
  }

  val found: Map<String, Int> = mapOf("children" to 3, "cats" to 7, "samoyeds" to 2, "pomeranians" to 3, "akitas" to 0, "vizslas" to 0, "goldfish" to 5, "trees" to 3, "cars" to 2, "perfumes" to 1)
  for (m in maps) {
    if (m.keys.filter { k -> k != "Sue" }.all { k -> m[k] == found[k] }) {
      println("Sue ${m["Sue"]} has all the things")
    }
  }
}