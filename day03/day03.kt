import java.io.File

fun main() {
    val inputFile = File("input")
    val content = inputFile.readText()

    // Regex to match valid mul(X,Y) where X and Y are 1-3 digit numbers
    val mulRegex = Regex("""mul\((\d{1,3}),\s*(\d{1,3})\)""")
    val doRegex = Regex("""do\(\)""")
    val dontRegex = Regex("""don't\(\)""")

    var isEnabled = true
    var sum = 0

    val matches = mulRegex.findAll(content) + doRegex.findAll(content) + dontRegex.findAll(content)
    matches.sortedBy { it.range.first }.forEach { match ->
        when {
            mulRegex.matches(match.value) -> {
                if (isEnabled) {
                    val (x, y) = match.destructured
                    sum += x.toInt() * y.toInt()
                }
            }

            doRegex.matches(match.value) -> isEnabled = true
            dontRegex.matches(match.value) -> isEnabled = false
        }
    }

    println("Total sum of all enabled mul operations: $sum");
}
