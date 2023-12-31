package at.gnu.adventofcode.year2023

class Day01(private val calibrationValues: List<String>) {

    fun part1(): Int =
        calibrationValues.sumOf { value ->
            val digits = value.filter { it.isDigit() }
            "${digits.first()}${digits.last()}".toInt()
        }

    fun part2(): Int =
        calibrationValues.sumOf { value ->
            val (_, firstNumber) = value.findAnyOf(NUMBERS) ?: (0 to "0")
            val (_, lastNumber) = value.findLastAnyOf(NUMBERS) ?: (0 to "0")
            ((NUMBERS.indexOf(firstNumber) % 10) * 10) + (NUMBERS.indexOf(lastNumber) % 10)
        }

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day01.txt"
        val NUMBERS = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    }
}

fun main() {
    val day01 = Day01(Day01::class.java.getResource(Day01.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day01::part1 -> ${day01.part1()}")
    println("Day01::part2 -> ${day01.part2()}")
}
