package at.gnu.adventofcode.year2023

import kotlin.math.abs

class Day11(image: List<String>) {

    data class Galaxy(val x: Int, val y: Int)

    private val universe = image.flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, c ->
            if (c == '#') Galaxy(x, y) else null
        }
    }

    private val emptyRows = image.mapIndexedNotNull { y, row -> if (row.all { it == '.' }) y else null }.toSet()

    private val emptyCols = buildSet { image.first().indices.forEach { x -> if (image.all { it[x] == '.' }) add(x) } }


    fun part1(size: Int): Long =
        universe.expand(size).calculateTotalDistance()

    fun part2(size: Int): Long =
        universe.expand(size).calculateTotalDistance()

    private fun List<Galaxy>.expand(size: Int): List<Galaxy> =
        map { galaxy ->
            val emptyColsBefore = emptyCols.count { it < galaxy.x }
            val emptyRowsBefore = emptyRows.count { it < galaxy.y }
            Galaxy(galaxy.x + (emptyColsBefore * (size - 1)), galaxy.y + (emptyRowsBefore * (size - 1)))
        }

    private fun List<Galaxy>.calculateTotalDistance(): Long =
        indices.sumOf { i -> ((i + 1) until size).sumOf { j -> calculateDistance(this[i], this[j]).toLong() } }

    private fun calculateDistance(galaxy1: Galaxy, galaxy2: Galaxy): Int =
        abs(galaxy1.x - galaxy2.x) + abs(galaxy1.y - galaxy2.y)

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day11.txt"
    }
}

fun main() {
    val day11 = Day11(Day11::class.java.getResource(Day11.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day11::part1 -> ${day11.part1(2)}")
    println("Day11::part2 -> ${day11.part2(1_000_000)}")
}
