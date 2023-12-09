package at.gnu.adventofcode.year2023

class Day09(input: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day09.txt"
    }

    private val report = input.map { it.split(" ").map(String::toLong) }

    fun part1(): Long {
        val predictions = mutableListOf<Long>()
        for (history in report) {
            val values = mutableListOf<Long>()
            var historyDelta = history
            while (historyDelta.any { it != 0L }) {
                values += historyDelta.last()
                historyDelta = historyDelta.zipWithNext().map { it.second - it.first }
            }
            predictions += values.sum()
        }
        return predictions.sum()
    }

    fun part2(): Long {
        val predictions = mutableListOf<Long>()
        for (history in report) {
            val values = mutableListOf<Long>()
            var historyDelta = history
            while (historyDelta.any { it != 0L }) {
                values += historyDelta.first()
                historyDelta = historyDelta.zipWithNext().map { it.second - it.first }
            }
            var extrapolation = 0L
            for (value in values.reversed())
                extrapolation = value - extrapolation
            predictions += extrapolation
        }
        return predictions.sum()
    }
}

fun main() {
    val day09 = Day09(Day09::class.java.getResource(Day09.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day09::part1 -> ${day09.part1()}")
    println("Day09::part2 -> ${day09.part2()}")
}
