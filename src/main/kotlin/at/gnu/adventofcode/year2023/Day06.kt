package at.gnu.adventofcode.year2023

class Day06(input: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day06.txt"
        val WHITESPACES = """\s+""".toRegex()
    }

    private val durations = input.first().split(WHITESPACES).drop(1).map(String::toLong)
    private val distances = input.drop(1).first().split(WHITESPACES).drop(1).map(String::toLong)

    fun part1(): Int =
        (durations zip distances).fold(1) { acc, (duration, winningDistance) ->
            acc * countWinningRaces(duration, winningDistance)
        }

    fun part2(): Int {
        val duration = durations.joinToString("", transform = Long::toString).toLong()
        val winningDistance = distances.joinToString("", transform = Long::toString).toLong()
        return countWinningRaces(duration, winningDistance)
    }

    private fun countWinningRaces(duration: Long, winningDistance: Long) =
        (1..duration).count { time ->
            val distance = time * (duration - time)
            distance > winningDistance
        }
}

fun main() {
    val day06 = Day06(Day06::class.java.getResource(Day06.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day06::part1 -> ${day06.part1()}")
    println("Day06::part2 -> ${day06.part2()}")
}
