fun main() {
    fun parseInput(input: List<String>): Pair<MutableList<MutableList<Char>>, List<CraneCommand>> {
        val (first, last) = input.partition { !it.startsWith("move") }

        val stackCount = first
            .first { it.isNotEmpty() && !it.contains("[") }
            .split(" ")
            .filter { it.isNotEmpty() }
            .maxOf { it.toInt() }

        val stacks = mutableListOf<MutableList<Char>>()
        repeat(stackCount) {
            stacks.add(mutableListOf<Char>())
        }
        first
            .filter { it.isNotEmpty() }
            .forEach {
                it.forEachIndexed { idx, cell ->
                    if (cell.isLetter()) {
                        val col = idx / 4
                        stacks[col].add(cell)
                    }
                }
            }

        val commandRegex = """move (\d+) from (\d+) to (\d+)""".toRegex()
        val commands = last.map { line ->
            val (_, count, from, to) = commandRegex.find(line)!!.groupValues
            CraneCommand(count.toInt(), from.toInt(), to.toInt())
        }

        return Pair(stacks, commands)
    }

    fun part1(input: List<String>): String {
        val (stacks, commands) = parseInput(input)
        commands.forEach { cmd ->
            repeat(cmd.count) {
                val crate = stacks[cmd.from - 1].removeFirst()
                stacks[cmd.to - 1].add(0, crate)
            }
        }

        return stacks
            .map { it.first() }
            .joinToString("")
    }

    fun part2(input: List<String>): String {
        val (stacks, commands) = parseInput(input)
        commands.forEach { cmd ->
            repeat(cmd.count) {
                val crate = stacks[cmd.from - 1].removeFirst()
                stacks[cmd.to - 1].add(it, crate)
            }
        }

        return stacks
            .map { it.first() }
            .joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
private data class CraneCommand(val count: Int, val from: Int, val to: Int)
