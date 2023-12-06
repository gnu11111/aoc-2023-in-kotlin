package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day06Test {

    private val input = listOf("Time:      7  15   30", "Distance:  9  40  200")

    private val test = mapOf(
        Day06::part1 to 288L,
        Day06::part2 to 71503L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day06 = Day06(input)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day06) }
            println("Day06::${function.name}: ${input.size} entries -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
