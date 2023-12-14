package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day12Test {

    private val input = listOf("???.### 1,1,3", ".??..??...?##. 1,1,3", "?#?#?#?#?#?#?#? 1,3,1,6",
        "????.#...#... 4,1,1", "????.######..#####. 1,6,5", "?###???????? 3,2,1")

    private val test = mapOf(
        Day12::part1 to 21L,
        Day12::part2 to 525152L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day12 = Day12(input)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day12) }
            println("Day12::${function.name}: ${input.size} entries -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
