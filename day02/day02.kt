import java.io.File

fun main() {
    // Read the input file
    val inputFile = File("input")
    val reports = inputFile.readLines()

    // Function to check if a report is safe
    fun isSafe(report: List<Int>): Boolean {
        if (report.size < 2) return false

        val differences = report.zipWithNext { a, b -> b - a }
        
        // Check if all differences are either increasing or decreasing
        val allIncreasing = differences.all { it in 1..3 }
        val allDecreasing = differences.all { it in -3..-1 }

        return allIncreasing || allDecreasing
    }

    // Parse the input and count safe reports
    val safeReportsCount = reports.count { line ->
        val levels = line.split(" ").map { it.toInt() }
        isSafe(levels)
    }

    // Output the result
    println("Number of safe reports: $safeReportsCount")
}
