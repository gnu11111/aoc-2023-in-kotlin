package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day07Test {

    private val input = listOf("32T3K 765", "T55J5 684", "KK677 28", "KTJJT 220", "QQQJA 483")

    private val test = mapOf(
        Day07::part1 to 6440L,
        Day07::part2 to 5905L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day07 = Day07(input)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day07) }
            println("Day07::${function.name}: ${input.size} entries -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
