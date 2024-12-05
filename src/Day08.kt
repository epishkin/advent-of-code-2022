fun main() {
    data class Location(val x: Int, val y: Int, val height: Int)

    class HeightMap(input: List<String>) {
        val map = input.mapIndexed { y, line ->
            line.mapIndexed { x, c -> Location(x, y, c.digitToInt()) }
        }

        val limitX = input[0].length - 1
        val limitY = input.size - 1

        fun getHeight(x: Int, y: Int) = map[y][x].height
        fun get(x: Int, y: Int) = map[y][x]

        fun isLowerThan(x: Int, y: Int, height: Int) = getHeight(x, y) < height

        fun validBounds(x: Int, y: Int) = (x in 0..limitX && y in 0..limitY)

        fun isVisible(x: Int, y: Int): Boolean {
            val directions = listOf(
                Pair(IntRange(0, x - 1), IntRange(y, y)),
                Pair(IntRange(x + 1, limitX), IntRange(y, y)),
                Pair(IntRange(x, x), IntRange(0, y - 1)),
                Pair(IntRange(x, x), IntRange(y + 1, limitY))
            )

//            directions.

            val adjacent = listOf(
                Pair(x - 1, y),
                Pair(x + 1, y),
                Pair(x, y - 1),
                Pair(x, y + 1)
            )
            val height = getHeight(x, y)
            return adjacent
                .filter { (x, y) -> validBounds(x, y) }
                .all { (x, y) -> isLowerThan(x, y, height) }
        }

        fun findLowPoints(): List<Location> {
            return IntRange(1, limitX - 1).flatMap { x ->
                IntRange(1, limitY - 1)
                    .filter { y -> isVisible(x, y)  }
                    .map { y-> get(x, y) }
            }
        }

        fun visibleCount(): Int {
            return map.size*2 + map[0].size*2 - 4 + findLowPoints().size
        }

    }

    fun part1(input: List<String>): Int {
        return HeightMap(input).visibleCount()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    assertEquals(part1(testInput), 21)
    assertEquals(part2(testInput), 0)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
