fun main() {
    fun simpleScore(line: String): Int {
        return when(line) {
            "A X" -> 1+3
            "A Y" -> 2+6
            "A Z" -> 3+0

            "B X" -> 1+0
            "B Y" -> 2+3
            "B Z" -> 3+6

            "C X" -> 1+6
            "C Y" -> 2+0
            "C Z" -> 3+3

            else -> error("Unexpected input $line")
        }
    }

    fun smartScore(line: String): Int {
        return when(line) {
            "A X" -> 3
            "A Y" -> 4
            "A Z" -> 8

            "B X" -> 1
            "B Y" -> 5
            "B Z" -> 9

            "C X" -> 2
            "C Y" -> 6
            "C Z" -> 7

            else -> error("Unexpected input $line")
        }
    }

    fun shapeScore(shape: ShapeType): Int {
        return when(shape) {
            ShapeType.ROCK     -> 1
            ShapeType.PAPER    -> 2
            ShapeType.SCISSORS -> 3
        }
    }

    fun outcomeScore(p1: ShapeType, p2: ShapeType): Int {
        return if (p1 == p2) 3
        else when(Pair(p1, p2)) {
            Pair(ShapeType.SCISSORS, ShapeType.ROCK)  -> 0
            Pair(ShapeType.ROCK, ShapeType.SCISSORS)  -> 6

            Pair(ShapeType.PAPER, ShapeType.ROCK)     -> 0
            Pair(ShapeType.ROCK, ShapeType.PAPER)     -> 6

            Pair(ShapeType.SCISSORS, ShapeType.PAPER) -> 0
            Pair(ShapeType.PAPER, ShapeType.SCISSORS) -> 6

            else -> error("Unexpected input $p1, $p2")
        }
    }

    fun toShape(txt: String): ShapeType {
        return when(txt) {
            "A" -> ShapeType.ROCK
            "X" -> ShapeType.ROCK

            "B" -> ShapeType.PAPER
            "Y" -> ShapeType.PAPER

            "C" -> ShapeType.SCISSORS
            "Z" -> ShapeType.SCISSORS

            else -> error("Unexpected shape: $txt")
        }
    }

    fun readInput(input: List<String>): List<Pair<ShapeType, ShapeType>> {
        return input
                .map { it.split(" ") }
                .map { Pair(toShape(it[0]), toShape(it[1])) }
    }

    fun part1(input: List<String>): Int {
//        println(input)
//        return readInput(input)
//                .also { println("$it") }
//                .map { (p1, p2) ->
//                    val shape = shapeScore(p2)
//                    val outcome = outcomeScore(p1, p2)
//                    shape + outcome
//                }
//                .also { println("$it") }
//                .sum()
        return input.sumOf { simpleScore(it) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { smartScore(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

private enum class ShapeType {
    ROCK,
    PAPER,
    SCISSORS
}
