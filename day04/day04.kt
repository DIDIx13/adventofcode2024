import java.io.File

fun main() {
    val grid = File("input").readLines().map { it.trim() }
    val rows = grid.size
    if (rows == 0) {
        println(0)
        return
    }
    val cols = grid[0].length
    val target = "MAS"
    val reversedTarget = target.reversed()
    var count = 0

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            if (grid[i][j] == 'A') {
                // Check NW-SE diagonal
                val nw = if (i - 1 in 0 until rows && j - 1 in 0 until cols) grid[i - 1][j - 1].toString() else ""
                val se = if (i + 1 in 0 until rows && j + 1 in 0 until cols) grid[i + 1][j + 1].toString() else ""
                val diagonal1 = nw + "A" + se

                // Check NE-SW diagonal
                val ne = if (i - 1 in 0 until rows && j + 1 in 0 until cols) grid[i - 1][j + 1].toString() else ""
                val sw = if (i + 1 in 0 until rows && j - 1 in 0 until cols) grid[i + 1][j - 1].toString() else ""
                val diagonal2 = ne + "A" + sw

                // Validate both diagonals
                val validDiagonal1 = diagonal1 == target || diagonal1 == reversedTarget
                val validDiagonal2 = diagonal2 == target || diagonal2 == reversedTarget

                if (validDiagonal1 && validDiagonal2) {
                    count++
                }
            }
        }
    }
    println(count)
}