import java.io.File

fun main() {
    val grid = File("input").readLines().map { it.trim() }
    val rows = grid.size
    val cols = grid[0].length
    val target = "XMAS"
    val directions = listOf(
        Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(-1, 1),
        Pair(0, -1), Pair(-1, 0), Pair(-1, -1), Pair(1, -1)
    )
    var count = 0

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            for ((dx, dy) in directions) {
                var found = true
                for (k in target.indices) {
                    val ni = i + dx * k
                    val nj = j + dy * k
                    if (ni !in 0 until rows || nj !in 0 until cols || grid[ni][nj] != target[k]) {
                        found = false
                        break
                    }
                }
                if (found) count++
            }
        }
    }
    println(count)
}