package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day02Test {

    private val games = listOf("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")

    private val test = mapOf(
        Day02::part1 to 8,
        Day02::part2 to 2286
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day02 = Day02(games)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day02) }
            println("Day02::${function.name}: ${games.size} games -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
