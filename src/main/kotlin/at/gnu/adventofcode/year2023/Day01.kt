package at.gnu.adventofcode.year2023

class Day01(private val calibrationValues: List<String>) {

    companion object {

        const val RESOURCE = "/adventofcode/year2023/Day01.txt"
        val numbers = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    }

    fun part1(): Long {
        var total = 0L
        calibrationValues.forEach { value ->
            val firstDigit = value.first { it.isDigit() }
            val lastDigit = value.last { it.isDigit() }
            total += (firstDigit.digitToInt() * 10) + lastDigit.digitToInt()
        }
        return total
    }

    fun part2(): Long {
        var total = 0L
        calibrationValues.forEach { value ->
            val firstDigit = value.indexOfFirst { it.isDigit() }
            val (firstIndex, firstNumber) = numbers.mapIndexed { i, s -> i to value.indexOf(s) }
                .filter { it.second >= 0 }.minByOrNull { it.second } ?: Pair(Int.MAX_VALUE, Int.MAX_VALUE)
            val lastDigit = value.indexOfLast { it.isDigit() }
            val (lastIndex, lastNumber) = numbers.mapIndexed { i, s -> i to value.lastIndexOf(s) }
                .filter { it.second >= 0 }.maxByOrNull { it.second } ?: Pair(-1, -1)
            val first = if ((firstDigit >= 0) && (firstDigit < firstNumber))
                value[firstDigit].digitToInt()
            else
                firstIndex
            val last = if ((lastDigit >= 0) && (lastDigit > lastNumber)) value[lastDigit].digitToInt() else lastIndex
            total += (first * 10L) + last
        }
        return total
    }
}

fun main() {
    val day01 = Day01(Day01::class.java.getResource(Day01.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day01::part1 -> ${day01.part1()}")
    println("Day01::part2 -> ${day01.part2()}")
}
