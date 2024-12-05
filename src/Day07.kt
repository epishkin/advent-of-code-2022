fun main() {
    data class Element(val name: String, val size: Int, val children: MutableMap<String, Element>) {
        fun totalSize(): Int {
            return size
        }
    }

    fun parseInput(input: List<String>): Element {
        val root = Element("/", 0, mutableMapOf<String, Element>())
        input.forEach { line ->
            println(line)
        }

        return root
    }

    fun part1(input: List<String>): Int {
        val root = parseInput(input)
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    assertEquals(part1(testInput), 95437)
    assertEquals(part2(testInput), 0)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
