fun main() {

    fun calculateMulSum(corruptedMemory: String): Int {
        // 使用正则表达式匹配有效的 mul(X,Y) 指令
        val regex = Regex("""mul\(\s*(\d{1,3})\s*,\s*(\d{1,3})\s*\)""")
        var totalSum = 0

        // 查找所有匹配的指令
        regex.findAll(corruptedMemory).forEach { matchResult ->
            // 提取 X 和 Y 的值
            val (x, y) = matchResult.destructured
            // 计算乘积并累加到总和
            totalSum += x.toInt() * y.toInt()
        }

        return totalSum
    }

    fun calculateEnabledMultiplications(input: String): Long {
        var isMulEnabled = true
        var totalSum = 0L

        // 使用正则表达式匹配乘法指令和 do()/don't() 指令
        val regex = Regex("(mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\))")
        val matches = regex.findAll(input)

        for (match in matches) {
            val matchedText = match.value
            when {
                matchedText.startsWith("mul") -> {
                    if (isMulEnabled) {
                        val numbers = matchedText.substringAfter("mul(").substringBefore(")").split(",")
                        val result = numbers[0].toLong() * numbers[1].toLong()
                        totalSum += result
                    }
                }
                matchedText == "do()" -> {
                    isMulEnabled = true
                }
                matchedText == "don't()" -> {
                    isMulEnabled = false
                }
            }
            println("$matchedText $isMulEnabled")
        }

        return totalSum
    }


    fun part1(input: List<String>): Int {
        return input.map { calculateMulSum(it) }.sum()
    }

    fun part2(input: List<String>): Long {
        return calculateEnabledMultiplications(input.joinToString(""))
    }

    val input = readInput("03")
    part1(input).println()
    part2(input).println()

}
