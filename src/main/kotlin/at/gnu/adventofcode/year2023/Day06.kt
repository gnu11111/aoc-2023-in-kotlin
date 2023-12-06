package at.gnu.adventofcode.year2023

class Day06(input: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day06.txt"
        val WHITESPACES = """\s+""".toRegex()
    }

    private val durations = input.first().split(WHITESPACES).drop(1).map(String::toInt)
    private val distances = input.drop(1).first().split(WHITESPACES).drop(1).map(String::toInt)

    fun part1(): Long {
        var result = 1L
        for ((duration, winningDistance) in durations zip distances) {
            var count = 0L
            for (time in 1..duration) {
                val distance = time * (duration - time)
                if (distance > winningDistance)
                    count++
            }
            result *= count
        }
        return result
    }

    fun part2(): Long {
        val duration = durations.joinToString("", transform = Int::toString).toLong()
        val winningDistance = distances.joinToString("", transform = Int::toString).toLong()
        var count = 0L
        for (time in 1..duration) {
            val distance = time * (duration - time)
            if (distance > winningDistance)
                count++
        }
        return count
    }
}

fun main() {
    val day06 = Day06(Day06::class.java.getResource(Day06.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day06::part1 -> ${day06.part1()}")
    println("Day06::part2 -> ${day06.part2()}")
}
