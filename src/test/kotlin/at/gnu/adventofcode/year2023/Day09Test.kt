package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day09Test {

    private val input = listOf("0 3 6 9 12 15", "1 3 6 10 15 21", "10 13 16 21 30 45")

    private val test = mapOf(
        Day09::part1 to 114L,
        Day09::part2 to 2L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day09 = Day09(input)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day09) }
            println("Day09::${function.name}: ${input.size} entries -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
