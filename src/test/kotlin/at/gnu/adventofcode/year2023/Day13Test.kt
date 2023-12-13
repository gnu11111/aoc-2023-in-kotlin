package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day13Test {

    private val input = listOf(
        listOf("#.##..##.", "..#.##.#.", "##......#", "##......#", "..#.##.#.", "..##..##.", "#.#.##.#."),
        listOf("#...##..#", "#....#..#", "..##..###", "#####.##.", "#####.##.", "..##..###", "#....#..#"))

    private val test = mapOf(
        Day13::part1 to 405,
        Day13::part2 to 400
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day13 = Day13(input)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day13) }
            println("Day13::${function.name}: ${input.size} patterns -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
