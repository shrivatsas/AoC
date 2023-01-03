import java.math.BigInteger
import java.security.MessageDigest

fun md5(secret: String, zeros: Int): Int {
    val md = MessageDigest.getInstance("MD5")
    val zstr = "0".repeat(zeros)
    return (1..Int.MAX_VALUE).fold(1) { i, _ ->
        val s = "$secret$i"
        val raw = BigInteger(1, md.digest(s.toByteArray()))
        val out = raw.toString(16).padStart(32, '0')
        if (out.substring(0, zeros) == zstr) return i else i + 1
    }
}

fun main() {
  println(md5("iwrupvqb", 5))
}
