import java.io.File

fun main() {
    val inputFile = File("input")
    val content = inputFile.readText()

    // Regex to match valid mul(X,Y) where X and Y are 1-3 digit numbers
    val regex = Regex("""mul\((\d{1,3}),\s*(\d{1,3})\)""")

    val sum = regex.findAll(content)
        .map { it.destructured }
        .map { (x, y) -> x.toInt() * y.toInt() }
        .sum()

    println("Total sum of all mul operations: $sum")
}
