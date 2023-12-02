package at.gnu.adventofcode.year2023

class Day02(input: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day02.txt"
        const val RED = "red"
        const val GREEN = "green"
        const val BLUE = "blue"
        val MAX_AMOUNTS = mapOf(RED to 12, GREEN to 13, BLUE to 14)
    }

    private val games = input.mapNotNull { line ->
        if (line.isBlank())
            null
        else {
            val parts = line.split(":")
            val reveals = parts[1].split(";").map { reveal ->
                reveal.split(",").map { cubes ->
                    val result = cubes.trim().split(" ")
                    result.first().toInt() to result.last().trim()
                }
            }
            val gameNumber = parts[0].split(" ")[1].trim().toInt()
            gameNumber to reveals
        }
    }

    fun part1(): Int =
        games.sumOf { game -> if (game.second.isValid()) game.first else 0 }

    fun part2(): Int =
        games.sumOf { game -> game.second.calculatePower() }

    private fun List<List<Pair<Int, String>>>.isValid(): Boolean =
        all { reveal ->
            reveal.all { cubes ->
                cubes.first <= MAX_AMOUNTS.getOrDefault(cubes.second, 0)
            }
        }

    private fun List<List<Pair<Int, String>>>.calculatePower(): Int {
        val amounts = mutableMapOf(RED to 0, GREEN to 0, BLUE to 0)
        forEach { reveal ->
            reveal.forEach { cubes ->
                amounts[cubes.second] = cubes.first.coerceAtLeast(amounts.getOrDefault(cubes.second, 0))
            }
        }
        return amounts.values.fold(1) { acc, amount -> acc * amount }
    }
}

fun main() {
    val day02 = Day02(Day02::class.java.getResource(Day02.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day02::part1 -> ${day02.part1()}")
    println("Day02::part2 -> ${day02.part2()}")
}
