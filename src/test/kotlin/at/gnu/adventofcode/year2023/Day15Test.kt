package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day15Test {

    private val initializationSequence = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7".split(",")

    private val test = mapOf(
        Day15::part1 to 1320,
        Day15::part2 to 145
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day15 = Day15(initializationSequence)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day15) }
            println("Day15::${function.name}: ${initializationSequence.size} entries -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
