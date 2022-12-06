fun main() {

    fun parseInput(input: List<String>) = input
        .map {
            it.split(",")
                .map { txt ->
                    val (start, end) = txt
                        .split("-")
                        .map { num -> num.trim().toInt() }
                    IntRange(start, end).toSet()
                }
        }

    fun part1(input: List<String>): Int {
        return parseInput(input)
            .count {
                val maxSize = it.maxOf { r -> r.size }
                val (one, two) = it
                one.union(two).size == maxSize
            }
    }

    fun part2(input: List<String>): Int {
        return parseInput(input)
            .count {
                val (one, two) = it
                one.intersect(two).isNotEmpty()
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
