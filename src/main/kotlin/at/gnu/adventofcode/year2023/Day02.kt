package at.gnu.adventofcode.year2023

class Day02(input: List<String>) {

    data class Reveal(val amount: Int, val color: String)
    data class Game(val number: Int, val reveals: List<List<Reveal>>)

    private val games = input.map { line ->
        val parts = line.split(":")
        val reveals = parts[1].split(";").map { reveal ->
            reveal.split(",").map { cubes ->
                val result = cubes.trim().split(" ")
                Reveal(result.first().toInt(), result.last().trim())
            }
        }
        val gameNumber = parts[0].split(" ")[1].trim().toInt()
        Game(gameNumber, reveals)
    }


    fun part1(): Int =
        games.sumOf { if (it.reveals.isValid()) it.number else 0 }

    fun part2(): Int =
        games.sumOf { it.reveals.calculatePower() }

    private fun List<List<Reveal>>.isValid(): Boolean =
        all { reveal ->
            reveal.all { cubes ->
                cubes.amount <= MAX_AMOUNTS.getOrDefault(cubes.color, 0)
            }
        }

    private fun List<List<Reveal>>.calculatePower(): Int {
        val amounts = mutableMapOf(RED to 0, GREEN to 0, BLUE to 0)
        forEach { reveal ->
            reveal.forEach { cubes ->
                amounts[cubes.color] = cubes.amount.coerceAtLeast(amounts.getOrDefault(cubes.color, 0))
            }
        }
        return amounts.values.fold(1) { acc, amount -> acc * amount }
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day02.txt"
        const val RED = "red"
        const val GREEN = "green"
        const val BLUE = "blue"
        val MAX_AMOUNTS = mapOf(RED to 12, GREEN to 13, BLUE to 14)
    }
}

fun main() {
    val day02 = Day02(Day02::class.java.getResource(Day02.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day02::part1 -> ${day02.part1()}")
    println("Day02::part2 -> ${day02.part2()}")
}
