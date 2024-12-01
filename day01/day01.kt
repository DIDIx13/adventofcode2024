import java.io.File
import kotlin.math.abs

fun main() {
    // Define the path to the input file
    val inputFilePath = "input"

    // Initialize lists to store the location IDs from the left and right groups
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    // Attempt to read the input file
    try {
        val file = File(inputFilePath)

        // Check if the file exists
        if (!file.exists()) {
            println("Error: The file '$inputFilePath' does not exist.")
            return
        }

        // Read the file line by line
        file.forEachLine { line ->
            if (line.isBlank()) return@forEachLine  // Skip empty lines

            // Split the line into parts based on whitespace (spaces or tabs)
            val parts = line.trim().split("\\s+".toRegex())

            if (parts.size >= 2) {  // Ensure there are at least two numbers in the line
                val left = parts[0].toIntOrNull()
                val right = parts[1].toIntOrNull()

                // Add the numbers to their respective lists if conversion is successful
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
        return
    }

    // Check that both lists have the same number of elements
    if (leftList.size != rightList.size) {
        println("Error: The left and right lists must have the same number of elements.")
        println("Left list size: ${leftList.size}, Right list size: ${rightList.size}")
        return
    }

    // Sort both lists in ascending order
    leftList.sort()
    rightList.sort()

    // Calculate the total distance by summing the absolute differences of paired elements
    val totalDistance = leftList.zip(rightList)
        .map { (left, right) -> abs(left - right) }
        .sum()

    // Print the total distance
    println("Total Distance: $totalDistance")
}
