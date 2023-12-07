package at.gnu.adventofcode.year2023

class Day07(input: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day07.txt"
    }

    class Round(val cards: String, val bid: Int, private var gameType: Int = 0) : Comparable<Round> {

        private val cardStrengths = listOf(
            listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'),
            listOf('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A')
        )

        override fun compareTo(other: Round): Int {
            val thisType: Int
            val otherType: Int
            if (gameType == 1) {
                thisType = calculateTypeWithJokers(cards)
                otherType = calculateTypeWithJokers(other.cards)
            } else {
                thisType = calculateTypeWithoutJokers(cards)
                otherType = calculateTypeWithoutJokers(other.cards)
            }
            return if (thisType != otherType)
                thisType.compareTo(otherType)
            else
                this.cards.compareStrength(other.cards)
        }

        private fun calculateTypeWithoutJokers(cards: String): Int {
            val amounts = cards.split("").filter { it.isNotBlank() }.groupingBy { it }.eachCount().toList()
                .map { (_, value) -> value }.sortedByDescending { it }
            val max = amounts.maxOrNull() ?: 0
            return when {
                max == 5 -> 6
                max == 4 -> 5
                max == 3 && amounts.contains(2) -> 4
                max == 3 -> 3
                amounts.count { it == 2 } == 2 -> 2
                max == 2 -> 1
                else -> 0
            }
        }

        private fun calculateTypeWithJokers(cards: String): Int {
            val amounts = cards.split("").filter { it.isNotBlank() && it != "J" }.groupingBy { it }.eachCount()
                .toList().map { (_, value) -> value }.sortedByDescending { it }
            val max = amounts.maxOrNull() ?: 0
            val jokers = cards.count { it == 'J' }
            return when {
                (max + jokers) == 5 -> 6
                (max + jokers) == 4 -> 5
                (max == 3) && amounts.contains(2) && (jokers == 0) -> 4
                (max == 2) && (amounts.count { it == 2 } == 2) && (jokers == 1) -> 4
                (max == 2) && (amounts.count { it == 2 } == 2) && (jokers == 0) -> 2
                (max + jokers) == 3 -> 3
                (max + jokers) == 2 -> 1
                else -> 0
            }
        }

        private fun String.compareStrength(other: String): Int {
            for ((thisCard, otherCard) in this zip other) {
                val thisStrength = cardStrengths[gameType].indexOf(thisCard)
                val otherStrength = cardStrengths[gameType].indexOf(otherCard)
                if (thisStrength != otherStrength)
                    return thisStrength.compareTo(otherStrength)
            }
            return 0
        }
    }

    private val rounds = input.map {
        val parts = it.split(" ")
        Round(parts[0].trim().uppercase(), parts[1].toInt())
    }

    fun part1(): Long =
        rounds.sorted().mapIndexed { i, round -> (i + 1L) * round.bid }.sum()

    fun part2(): Long {
//        rounds.forEach { println("${it.cards.split("").sorted().joinToString("")}: " +
//                    "${Round(it.cards, it.bid, 1).calculateTypeWithJokers(it.cards)}") }
        return rounds.map { Round(it.cards, it.bid, 1) }.sorted().mapIndexed { i, round -> (i + 1L) * round.bid }.sum()
    }
}

fun main() {
    val day07 = Day07(Day07::class.java.getResource(Day07.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day07::part1 -> ${day07.part1()}")
    println("Day07::part2 -> ${day07.part2()}")
}
