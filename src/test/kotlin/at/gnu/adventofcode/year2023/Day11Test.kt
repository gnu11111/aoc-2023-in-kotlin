package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day11Test {

    private val image = listOf("...#......", ".......#..", "#.........", "..........", "......#...", ".#........",
        ".........#", "..........", ".......#..", "#...#.....")

    private val test = mapOf(
        Day11::part1 to 2 to 374L,
        Day11::part2 to 10 to 1030L,
        Day11::part2 to 100 to 8410L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day11 = Day11(image)
        for ((function, size) in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day11, size) }
            println("Day11::${function.name}: ${image.size} rows, expansion-size: $size -> $result [${time}]")
            assertEquals(test[function to size], result)
        }
    }
}
