package at.gnu.adventofcode.year2023

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day10Test {

    private val areas = listOf(listOf(".....", ".S-7.", ".|.|.", ".L-J.", "....."),
        listOf("..F7.", ".FJ|.", "SJ.L7", "|F--J", "LJ..."),
        listOf("...........", ".S-------7.", ".|F-----7|.", ".||.....||.", ".||.....||.", ".|L-7.F-J|.",
            ".|..|.|..|.", ".L--J.L--J.", "..........."),
        listOf("..........", ".S------7.", ".|F----7|.", ".||....||.", ".||....||.", ".|L-7F-J|.", ".|..||..|.",
            ".L--JL--J.", ".........."),
        listOf(".F----7F7F7F7F-7....", ".|F--7||||||||FJ....", ".||.FJ||||||||L7....", "FJL7L7LJLJ||LJ.L-7..",
            "L--J.L7...LJS7F-7L7.", "....F-J..F7FJ|L7L7L7", "....L7.F7||L7|.L7L7|", ".....|FJLJ|FJ|F7|.LJ",
            "....FJL-7.||.||||...", "....L---J.LJ.LJLJ..."),
        listOf("FF7FSF7F7F7F7F7F---7", "L|LJ||||||||||||F--J", "FL-7LJLJ||||||LJL-77", "F--JF--7||LJLJ7F7FJ-",
            "L---JF-JLJ.||-FJLJJ7", "|F|F-JF---7F7-L7L|7|", "|FFJF7L7F-JF7|JL---7", "7-L-JL7||F7|L7F-7F7|",
            "L.L7LFJ|||||FJL7||LJ", "L7JLJL-JLJLJL--JLJ.L"))

    private val test = mapOf(
        (Day10::part1 to areas[0]) to 4,
        (Day10::part1 to areas[1]) to 8,
        (Day10::part2 to areas[2]) to 4,
        (Day10::part2 to areas[3]) to 4,
        (Day10::part2 to areas[4]) to 8,
        (Day10::part2 to areas[5]) to 10
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        for (key in test.keys) {
            val (result, time) = measureTimedValue { key.first.invoke(Day10(key.second)) }
            println("Day10::${key.first.name}: ${areas.size} lines -> $result [$time]")
            assertEquals(test[key], result)
        }
    }
}
