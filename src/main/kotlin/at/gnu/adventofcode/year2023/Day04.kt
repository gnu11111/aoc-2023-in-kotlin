package at.gnu.adventofcode.year2023

import kotlin.math.pow

class Day04(input: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day04.txt"
        val WHITESPACES = """\s+""".toRegex()
    }

    data class Card(val winningNumbers: Set<Int>, val myNumbers: Set<Int>)

    private val cards = input.map { line ->
        val allNumbers = line.split(":")[1].split("|")
        val winningNumbers = allNumbers[0].trim().split(WHITESPACES).map(String::toInt).toSet()
        val myNumbers = allNumbers[1].trim().split(WHITESPACES).map(String::toInt).toSet()
        Card(winningNumbers, myNumbers)
    }

    fun part1(): Int =
        cards.fold(0) { acc, card ->
            val winning = (card.winningNumbers intersect card.myNumbers).size
            if (winning > 0) acc + 2.0.pow(winning - 1.0).toInt() else acc
        }

    fun part2(): Int {
        val winningCards = Array(cards.size) { 1 }
        for ((cardNumber, numbers) in cards.withIndex()) {
            val winning = (numbers.winningNumbers intersect numbers.myNumbers).size
            (1..winning).forEach { winningCards[cardNumber + it] += winningCards[cardNumber] }
        }
        return winningCards.sum()
    }
}

fun main() {
    val day04 = Day04(Day04::class.java.getResource(Day04.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day04::part1 -> ${day04.part1()}")
    println("Day04::part2 -> ${day04.part2()}")
}
