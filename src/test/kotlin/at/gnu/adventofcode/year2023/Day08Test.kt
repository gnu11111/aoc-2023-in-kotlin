package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day08Test {

    private val inputs = listOf(Pair("RL", listOf("AAA = (BBB, CCC)", "BBB = (DDD, EEE)", "CCC = (ZZZ, GGG)",
            "DDD = (DDD, DDD)", "EEE = (EEE, EEE)", "GGG = (GGG, GGG)", "ZZZ = (ZZZ, ZZZ)")),
        Pair("LLR", listOf("AAA = (BBB, BBB)", "BBB = (AAA, ZZZ)", "ZZZ = (ZZZ, ZZZ)")),
        Pair("LR", listOf("11A = (11B, XXX)", "11B = (XXX, 11Z)", "11Z = (11B, XXX)", "22A = (22B, XXX)",
            "22B = (22C, 22C)", "22C = (22Z, 22Z)", "22Z = (22B, 22B)", "XXX = (XXX, XXX)")))

    private val test = mapOf(
        Day08::part1 to inputs[0] to 2L,
        Day08::part1 to inputs[1] to 6L,
        Day08::part2 to inputs[2] to 6L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        for (function in test.keys) {
            val day08 = Day08(function.second.first, function.second.second)
            val (result, time) = measureTimedValue { function.first.invoke(day08) }
            println("Day08::${function.first.name}: ${function.second.second.size} nodes -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
