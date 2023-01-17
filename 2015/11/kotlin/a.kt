fun nextPassword(current: String): String {
    var password = incrementPassword(current)
    while (!isValidPassword(password)) {
        password = incrementPassword(password)
    }
    return password
}

fun incrementPassword(password: String): String {
    var chars = password.toCharArray()
    var carry = 1
    for (i in chars.size - 1 downTo 0) {
        var c = chars[i]
        if (c == 'z') {
            chars[i] = 'a'
            carry = 1
        } else {
            chars[i] = c + carry
            carry = 0
            break
        }
    }
    return String(chars)
}

fun isValidPassword(password: String): Boolean {
    return hasStraight(password) && hasNoConfusingLetters(password) && hasTwoPairs(password)
}

fun hasStraight(password: String): Boolean {
    for (i in 0..password.length - 3) {
        if (password[i] + 1 == password[i + 1] && password[i + 1] + 1 == password[i + 2]) {
            return true
        }
    }
    return false
}

fun hasNoConfusingLetters(password: String): Boolean {
    val regex = "[iol]".toRegex()
    return !regex.containsMatchIn(password)
}

fun hasTwoPairs(password: String): Boolean {
    var pairs = 0
    var skip = true
    for (i in 0..password.length - 2) {
        if (skip) {
            skip = false
            continue
        }
        if (password[i] == password[i + 1]) {
            pairs++
            skip = true
        }
    }
    return pairs >= 2
}

fun main() {
    val current = "hxbxwxba"
    println("Next password: ${nextPassword(current)}")
    println("Next password: ${nextPassword(nextPassword(current))}")
}