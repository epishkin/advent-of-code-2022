fun main() {

    fun priority(element: Char): Int {
        val offset =
                if (element.isUpperCase()) 'A'.code - 27
                else 'a'.code - 1
        return element.code - offset
    }

    fun part1(input: List<String>): Int {
        return input
                .map {
                    val half = it.length / 2
                    Pair(it.take(half), it.takeLast(half))
                }
                .map { (one, two) ->
                    one.find { two.contains(it) }
                }
                .sumOf {  priority(it!!) }
    }

    fun part2(input: List<String>): Int {
        return input
                .chunked(3)
                .map { (one, two, three) ->
                    one
                      .toSet()
                      .intersect(two.toSet())
                      .intersect(three.toSet())
                      .first()
                }
                .sumOf { priority(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
