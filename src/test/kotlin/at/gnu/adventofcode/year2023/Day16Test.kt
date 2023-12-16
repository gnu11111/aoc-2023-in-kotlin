package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day16Test {

    private val layout = listOf(".|...\\....", "|.-.\\.....", ".....|-...", "........|.", "..........", ".........\\",
        "..../.\\\\..", ".-.-/..|..", ".|....-|.\\", "..//.|....")

    private val test = mapOf(
        Day16::part1 to 46,
        Day16::part2 to 51
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day16 = Day16(layout)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day16) }
            println("Day16::${function.name}: ${layout.size} rows -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
