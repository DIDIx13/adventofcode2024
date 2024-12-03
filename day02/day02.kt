import java.io.File

fun main() {
    val inputFile = File("input")
    val reports = inputFile.readLines()

    // Function to check if a report is safe (Part 1=
    fun isSafe(report: List<Int>): Boolean {
        if (report.size < 2) return false
        val differences = report.zipWithNext { a, b -> b - a }
        val allIncreasing = differences.all { it in 1..3 }
        val allDecreasing = differences.all { it in -3..-1 }
        return allIncreasing || allDecreasing
    }

    // Function to check if a report can be made safe by removing one level
    fun canBeMadeSafe(report: List<Int>): Boolean {
        if (report.size <= 2) return true // Removing one level always results in a valid report for lists of size <= 2
        return report.indices.any { index ->
            val modifiedReport = report.filterIndexed { i, _ -> i != index }
            isSafe(modifiedReport)
        }
    }

    // Count reports that are either safe or can be made safe
    val safeReportsCount = reports.count { line ->
        val levels = line.split(" ").map { it.toInt() }
        isSafe(levels) || canBeMadeSafe(levels)
    }

    println("Number of safe reports with problem dampener: $safeReportsCount")
}