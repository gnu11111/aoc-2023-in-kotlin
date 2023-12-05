package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day05Test {

    private val input = listOf(
        listOf("seeds: 79 14 55 13"),
        listOf("seed-to-soil map:", "50 98 2", "52 50 48"),
        listOf("soil-to-fertilizer map:", "0 15 37", "37 52 2", "39 0 15"),
        listOf("fertilizer-to-water map:", "49 53 8", "0 11 42", "42 0 7", "57 7 4"),
        listOf("water-to-light map:", "88 18 7", "18 25 70"),
        listOf("light-to-temperature map:", "45 77 23", "81 45 19", "68 64 13"),
        listOf("temperature-to-humidity map:", "0 69 1", "1 0 69"),
        listOf("humidity-to-location map:", "60 56 37", "56 93 4")
    )

    private val test = mapOf(
        Day05::part1 to 35L,
        Day05::part2 to 46L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day05 = Day05(input)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day05) }
            println("Day05::${function.name}: ${input.size} entries -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
