package at.gnu.adventofcode.year2023

import kotlin.math.pow

class Day04(input: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day04.txt"
        val WHITESPACES = """\s+""".toRegex()
    }

    private val cards = input.mapNotNull { line ->
        if (line.isBlank())
            null
        else {
            val parts = line.split(":")
            val cardNumber = parts[0].trim().split(WHITESPACES)[1].trim().toInt()
            val allNumbers = parts[1].split("|")
            val winningNumbers = allNumbers[0].trim().split(WHITESPACES).map(String::toInt).toSet()
            val myNumbers = allNumbers[1].trim().split(WHITESPACES).map(String::toInt).toSet()
            Triple(cardNumber, winningNumbers, myNumbers)
        }
    }

    fun part1(): Int =
        cards.fold(0) { acc, card ->
            val winning = (card.second intersect card.third).size
            if (winning > 0) acc + 2.0.pow(winning - 1.0).toInt() else acc
        }

    fun part2(): Int {
        val winningCards = Array(cards.maxOf { it.first } + 1) { 0 }
        for ((cardNumber, winningNumbers, myNumbers) in cards) {
            val winning = (winningNumbers intersect myNumbers).size
            winningCards[cardNumber]++
            if (winning > 0)
                ((cardNumber + 1)..(cardNumber + winning)).forEach { winningCards[it] += winningCards[cardNumber] }
//            println("$cardNumber: ${winningCards.toList().drop(1)}")
        }
        return winningCards.sum()
    }
}

fun main() {
    val day04 = Day04(Day04::class.java.getResource(Day04.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day04::part1 -> ${day04.part1()}")
    println("Day04::part2 -> ${day04.part2()}")
}
