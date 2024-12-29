fun main() {
    fun countWordOccurrences(grid: List<String>, word: String): Int {
        val rows = grid.size
        val cols = grid[0].length
        val directions = listOf(
            Pair(0, 1),   // 水平向右
            Pair(0, -1),  // 水平向左
            Pair(1, 0),   // 垂直向下
            Pair(-1, 0),  // 垂直向上
            Pair(1, 1),   // 对角线：左上到右下
            Pair(-1, -1), // 对角线：右下到左上
            Pair(1, -1),  // 对角线：左下到右上
            Pair(-1, 1)   // 对角线：右上到左下
        )

        fun isWordAt(x: Int, y: Int, dx: Int, dy: Int): Boolean {
            for (i in word.indices) {
                val nx = x + i * dx
                val ny = y + i * dy
                if (nx !in 0 until rows || ny !in 0 until cols || grid[nx][ny] != word[i]) {
                    return false
                }
            }
            return true
        }

        var count = 0
        for (x in 0 until rows) {
            for (y in 0 until cols) {
                for ((dx, dy) in directions) {
                    if (isWordAt(x, y, dx, dy)) {
                        count++
                    }
                }
            }
        }
        return count
    }
    fun part1(input: List<String>): Int {
        return countWordOccurrences(input, "XMAS")
    }

    fun countXMas(matrix: List<String>): Int {
        val n = matrix.size
        val m = matrix[0].length
        var count = 0

        // Helper function to check for "MAS" in a given direction
        fun checkForMas(x: Int, y: Int, dx: Int, dy: Int): Boolean {
            val mas = "MAS"
            val sam = "SAM"
            val strBuilder = StringBuilder()
            for (i in -1 until 2) {
                val newX = x + i * dx
                val newY = y + i * dy
                if (newX < 0 || newX >= n || newY < 0 || newY >= m) {
                    return false
                }
                strBuilder.append(matrix[newX][newY])
            }
            return strBuilder.toString() == mas || strBuilder.toString() == sam
        }

        // Iterate through the matrix to find "X-MAS"
        for (i in 0 until n) {
            for (j in 0 until m) {
                // Check for "X-MAS" in all four diagonal directions
                if (checkForMas(i, j, 1, 1) && checkForMas(i, j, 1, -1)) count++ // Down-Right and Up-Left
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        return countXMas(input)
    }

    val input = readInput("04")
    part1(input).println()
    part2(input).println()
}