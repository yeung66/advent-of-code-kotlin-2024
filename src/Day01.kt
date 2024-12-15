import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (left, right) = input
                .asSequence()
                .map { it.split("   ").map { num -> num.toInt() } }
                .fold(mutableListOf<Int>() to mutableListOf<Int>()) { acc, nums ->
                    acc.first.add(nums[0])
                    acc.second.add(nums[1])
                    acc
                }
        left.sort()
        right.sort()
        return left.zip(right).sumOf { (l, r) -> abs(l - r) }
    }

    fun part2(input: List<String>): Int {
        val (left, right) = input
            .asSequence()
            .map { it.split("   ").map { num -> num.toInt() } }
            .fold(mutableListOf<Int>() to mutableMapOf<Int, Int>()) { acc, nums ->
                acc.first.add(nums[0])
                acc.second.put(nums[1], acc.second.getOrDefault(nums[1], 0) + 1)
                acc
            }
        return left.map { num -> num * right.getOrDefault(num, 0) }.sum()
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("01")
    part1(input).println()
    part2(input).println()

}
