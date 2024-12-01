import java.io.File
import kotlin.math.abs

fun main() {
    val inputFilePath = "input"

    // Read and parse the input file
    val (leftList, rightList) = readInput(inputFilePath) ?: return

    // Calculate Total Distance (Part One)
    val totalDistance = calculateTotalDistance(leftList, rightList)

    // Calculate Similarity Score (Part Two)
    val similarityScore = calculateSimilarityScore(leftList, rightList)

    // Output the Results
    println("=== Results ===")
    println("Total Distance (Part One): $totalDistance")
    println("Similarity Score (Part Two): $similarityScore")
}

/**
 * Reads the input file and separates the data into two lists.
 *
 * @param filePath The path to the input file.
 * @return A Pair containing the left and right lists, or null if an error occurs.
 */
fun readInput(filePath: String): Pair<List<Int>, List<Int>>? {
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    try {
        val file = File(filePath)

        // Check if the file exists
        if (!file.exists()) {
            println("Error: The file '$filePath' does not exist.")
            return null
        }

        // Read the file line by line
        file.forEachLine { line ->
            if (line.isBlank()) return@forEachLine  // Skip empty lines

            // Split the line into parts based on whitespace (spaces or tabs)
            val parts = line.trim().split("\\s+".toRegex())

            if (parts.size >= 2) {  // Ensure there are at least two numbers in the line
                val left = parts[0].toIntOrNull()
                val right = parts[1].toIntOrNull()

                if (left != null && right != null) {
                    leftList.add(left)
                    rightList.add(right)
                } else {
                    println("Warning: Skipping line due to invalid numbers -> '$line'")
                }
            } else {
                println("Warning: Skipping line due to insufficient data -> '$line'")
            }
        }

    } catch (e: Exception) {
        println("An error occurred while reading the file: ${e.message}")
        return null
    }

    // Validate that both lists have the same number of elements
    if (leftList.size != rightList.size) {
        println("Error: The left and right lists must have the same number of elements.")
        println("Left list size: ${leftList.size}, Right list size: ${rightList.size}")
        return null
    }

    return Pair(leftList, rightList)
}

/**
 * Calculates the total distance between two lists by pairing sorted elements.
 *
 * @param leftList The first list of integers.
 * @param rightList The second list of integers.
 * @return The total distance as an integer.
 */
fun calculateTotalDistance(leftList: List<Int>, rightList: List<Int>): Int {
    val sortedLeft = leftList.sorted()
    val sortedRight = rightList.sorted()

    return sortedLeft.zip(sortedRight)
        .sumOf { (left, right) -> abs(left - right) }
}

/**
 * Calculates the similarity score by summing each number in the left list
 * multiplied by its frequency in the right list.
 *
 * @param leftList The first list of integers.
 * @param rightList The second list of integers.
 * @return The similarity score as an integer.
 */
fun calculateSimilarityScore(leftList: List<Int>, rightList: List<Int>): Int {
    val rightFrequencyMap = rightList.groupingBy { it }.eachCount()

    return leftList.sumOf { number ->
        val frequency = rightFrequencyMap[number] ?: 0
        number * frequency
    }
}
