package at.gnu.adventofcode.year2023

class Day01(private val calibrationValues: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day01.txt"
        val numbers = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    }

    fun part1(): Int =
        calibrationValues.fold(0) { acc, value ->
            val digits = value.filter { it.isDigit() }
            acc + "${digits.first()}${digits.last()}".toInt()
        }

    fun part2(): Int =
        calibrationValues.fold(0) { acc, value ->
            val firstDigit = numbers.mapIndexed { i, number -> i to value.indexOf(number) }
                .filter { it.second >= 0 }.minByOrNull { it.second }?.first ?: 0
            val lastDigit = numbers.mapIndexed { i, s -> i to value.lastIndexOf(s) }
                .filter { it.second >= 0 }.maxByOrNull { it.second }?.first ?: 0
            acc + ((firstDigit % 10) * 10) + (lastDigit % 10)
        }
}

fun main() {
    val day01 = Day01(Day01::class.java.getResource(Day01.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day01::part1 -> ${day01.part1()}")
    println("Day01::part2 -> ${day01.part2()}")
}
