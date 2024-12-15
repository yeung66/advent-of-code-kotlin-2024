import kotlin.math.abs

fun main() {
    fun checkSafe(levels: List<Int>, skip: Int = -1): Boolean {
        if (skip != -1) {
            val newLevels = levels.toMutableList()
            newLevels.removeAt(skip)
            return checkSafe(newLevels)
        }
        // check if all increasing or decreasing
        val same = levels == levels.sorted() || levels == levels.sortedDescending()
        if (!same) return false
        // check if there are two adjacent numbers' difference is between 1 and 3
        val diff = levels.zipWithNext().map { (a, b) -> b - a }
        return diff.all { abs(it) in 1..3 }
    }

    fun part1(input: List<String>): Int {
        return input.map { it.split(" ").map { num -> num.toInt() } }
            .filter { checkSafe(it) }
            .size
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split(" ").map { num -> num.toInt() } }
            .filter { checkSafe(it) || (0 until it.size).any { i -> checkSafe(it, i) } }
            .size

    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("02")
    part1(input).println()
    part2(input).println()

}
