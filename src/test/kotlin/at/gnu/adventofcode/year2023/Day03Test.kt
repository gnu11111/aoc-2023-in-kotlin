package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day03Test {

    private val engineSchematic = listOf("467..114..", "...*......", "..35..633.", "......#...", "617*......", ".....+.58.",
        "..592.....", "......755.", "...\$.*....", ".664.598..")

    private val test = mapOf(
        Day03::part1 to 4361,
        Day03::part2 to 467835
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day03 = Day03(engineSchematic)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day03) }
            println("Day03::${function.name}: ${engineSchematic.size} lines -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
