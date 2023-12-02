package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day01Test {

    private val calibrationValues = listOf(listOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"),
        listOf("two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234",
            "7pqrstsixteen"), listOf("stwoone4eightwoj"))

    private val test = mapOf(
/*
        Day01::part1 to calibrationValues[0] to 142L,
        Day01::part2 to calibrationValues[1] to 281L,
*/
        Day01::part2 to calibrationValues[2] to 22L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        for ((function, input) in test.keys) {
            val (result, time) = measureTimedValue { function(Day01(input)) }
            println("Day01::${function.name}: ${input.size} calibration values -> $result [$time]")
            assertEquals(test[function to input], result)
        }
    }
}
