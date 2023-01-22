import java.io.File
import kotlin.math.max

data class Ingredient(val name: String, val capacity: Int, val durability: Int, val flavor: Int, val texture: Int, val calories: Int)


fun main() {
  val lines: List<String> = File("../15.input").useLines { it.toList() }

  // Sugar: capacity 3, durability 0, flavor 0, texture -3, calories 2
  val regex = Regex("""(\w+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)""")
  val ingredients = lines.map { line ->
    val (name, capacity, durability, flavor, texture, calories) = regex.matchEntire(line)!!.destructured
    Ingredient(name, capacity.toInt(), durability.toInt(), flavor.toInt(), texture.toInt(), calories.toInt())
  }

  var max_score = 0
  for (i in 0..100) {
    for (j in 0..100 - i) {
      for (k in 0..100 - i - j) {
        val l = 100 - i - j - k
        max_score = max(mapCalculateScore(ingredients, listOf(i, j, k, l)), max_score)
  //       max_score = max(itCalculateScore(ingredients, i, j, k, l), max_score)
      }
    }
  }

  println(max_score)
}

// a solution that uses a loop to make it more
fun itCalculateScore(ingredients: List<Ingredient>, i: Int, j: Int, k: Int, l: Int): Int {
  val capacity = i * ingredients[0].capacity + j * ingredients[1].capacity + k * ingredients[2].capacity + l * ingredients[3].capacity
  val durability = i * ingredients[0].durability + j * ingredients[1].durability + k * ingredients[2].durability + l * ingredients[3].durability
  val flavor = i * ingredients[0].flavor + j * ingredients[1].flavor + k * ingredients[2].flavor + l * ingredients[3].flavor
  val texture = i * ingredients[0].texture + j * ingredients[1].texture + k * ingredients[2].texture + l * ingredients[3].texture
  if (capacity > 0 && durability > 0 && flavor > 0 && texture > 0) {
    return capacity * durability * flavor * texture
  }
  return 0
}

// an alternative solution that uses a more functional style
fun mapCalculateScore(ingredients: List<Ingredient>, amounts: List<Int>): Int {
  val capacity = amounts.zip(ingredients).map { (amount, ingredient) -> amount * ingredient.capacity }.sum()
  val durability = amounts.zip(ingredients).map { (amount, ingredient) -> amount * ingredient.durability }.sum()
  val flavor = amounts.zip(ingredients).map { (amount, ingredient) -> amount * ingredient.flavor }.sum()
  val texture = amounts.zip(ingredients).map { (amount, ingredient) -> amount * ingredient.texture }.sum()
  return max(0, capacity) * max(0, durability) * max(0, flavor) * max(0, texture)
}
