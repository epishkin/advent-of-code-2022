fun main() {
    fun readElves(input: List<String>): List<List<Int>> {
        var caloriesByElf = mutableListOf<List<Int>>()
        var elf = mutableListOf<Int>()
        for (row in input) {
            if (row.isEmpty()) {
                caloriesByElf.add(elf)
                elf = mutableListOf<Int>()
            } else {
                elf.add(row.toInt())
            }
        }
        if (elf.isNotEmpty()) {
            caloriesByElf.add(elf)
        }

        return caloriesByElf
    }

    fun part1(input: List<String>): Int {
        var caloriesByElf = readElves(input)

        return caloriesByElf.maxOf { it.sum() }
    }

    fun part2(input: List<String>): Int {
        var caloriesByElf = readElves(input)

        return caloriesByElf.map{it.sum()}.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
