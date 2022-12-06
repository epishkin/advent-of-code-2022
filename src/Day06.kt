fun main() {

    fun findMarker(input: String, size: Int): Int {
        val marker = input
            .windowed(size)
            .indexOfFirst {
                it.toSet().size == size
            }
        return marker + size
    }

    // test if implementation meets criteria from the description, like:
    fun testInput(fileName: String, markerSize: Int) {
        readInput(fileName).forEach { line ->
            val (marker, text) = line.split(" ")
            assertEquals(findMarker(text, markerSize), marker.toInt())
        }
    }

    testInput("Day06-part1_test", 4)
    testInput("Day06-part2_test", 14)

    val input = readInput("Day06")[0]
    println(findMarker(input, 4))
    println(findMarker(input, 14))
}
