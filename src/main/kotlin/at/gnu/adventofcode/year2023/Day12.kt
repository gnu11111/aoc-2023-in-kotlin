package at.gnu.adventofcode.year2023

class Day12(input: List<String>) {

    data class Record(val positions: String, val groups: List<Int>)

    private val records = input.map {
        val parts = it.split(" ")
        val positions = parts[0]
        val amounts = parts[1].split(",").map(String::toInt)
        Record(positions, amounts)
    }


    fun part1(): Long =
        records.sumOf { countArrangements(it.positions, it.groups) }

    fun part2(): Long =
        records.sumOf { record ->
            val unfoldedPositions = (0..4).joinToString("?") { record.positions }
            val unfoldedGroups = (0..4).flatMap { record.groups }
            countArrangements(unfoldedPositions, unfoldedGroups)
        }

    private fun countArrangements(positions: String, groups: List<Int>,
                                  cache: MutableMap<Pair<String, List<Int>>, Long> = mutableMapOf()): Long {
        val key = positions to groups
        cache[key]?.let { return it }

        val position = positions.firstOrNull()
        val group = groups.firstOrNull() ?: 0
        val remainingGroups = groups.drop(1)

        return when {
            positions.isEmpty() && groups.isEmpty() -> 1
            positions.isEmpty() -> 0

            (position == '.') -> countArrangements(positions.dropWhile { it == '.' }, groups, cache)

            (position == '?') -> countArrangements(positions.drop(1), groups, cache) +
                    countArrangements("#${positions.drop(1)}", groups, cache)

            (position == '#') && groups.isEmpty() -> 0
            (position == '#') && ((group > positions.length) || positions.take(group).any { it == '.' }) -> 0
            (position == '#') && (group == positions.length) && remainingGroups.isEmpty() -> 1
            (position == '#') && ((group == positions.length) || (positions[group] == '#')) -> 0
            (position == '#') -> countArrangements(positions.drop(group + 1), remainingGroups, cache)

            else -> error("Invalid positions: $positions")
        }.apply { cache[key] = this }
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day12.txt"
    }
}

fun main() {
    val day12 = Day12(Day12::class.java.getResource(Day12.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day12::part1 -> ${day12.part1()}")
    println("Day12::part2 -> ${day12.part2()}")
}
