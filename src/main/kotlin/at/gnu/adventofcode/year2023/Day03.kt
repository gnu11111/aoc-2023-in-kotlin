package at.gnu.adventofcode.year2023

class Day03(private val engineSchematic: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day03.txt"
    }

    fun part1(): Int =
        findPartNumbers().sumOf { it.first }

    fun part2(): Int {
        val starMap = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()
        findPartNumbers().forEach { number ->
            number.second.forEach { star ->
                if (starMap[star] == null)
                    starMap[star] = mutableListOf(number.first)
                else
                    starMap[star]!!.add(number.first)
            }
        }
//        println(starMap)
        return starMap.filter { it.value.size == 2 }.map { it.value[0] * it.value[1] }.sum()
    }

    private fun findPartNumbers(): MutableList<Pair<Int, Set<Pair<Int, Int>>>> {
        val partNumbers = mutableListOf<Pair<Int, Set<Pair<Int, Int>>>>()
        val illegalNumbers = mutableListOf<Int>()
        engineSchematic.forEachIndexed { y, line ->
//            val numbers = line.split("""\D+""".toRegex()).filter(String::isNotBlank).map(String::toInt)
            var inNumber = false
            var partNumber = false
            var number = 0
            var stars = mutableSetOf<Pair<Int, Int>>()
            line.forEachIndexed { x, c ->
                when {
                    c.isDigit() && !inNumber -> {
                        inNumber = true
                        number = c.digitToInt()
                        partNumber = checkNeighbors(x, y)
                        stars += collectStars(x, y)
                    }
                    c.isDigit() && inNumber -> {
                        number = (number * 10) + c.digitToInt()
                        partNumber = partNumber || checkNeighbors(x, y)
                        stars += collectStars(x, y)
                    }
                    inNumber -> {
                        inNumber = false
                        if (partNumber) partNumbers += Pair(number, stars) else illegalNumbers += number
                        partNumber = false
                        stars = mutableSetOf()
                    }
                }
            }
            if (inNumber) {
                inNumber = false
                if (partNumber) partNumbers += Pair(number, stars) else illegalNumbers += number
                partNumber = false
                stars = mutableSetOf()
            }
        }
//        println(partNumbers)
//        println(illegalNumbers)
        return partNumbers
    }

    private fun collectStars(x: Int, y: Int): Set<Pair<Int, Int>> {
        val stars = mutableSetOf<Pair<Int, Int>>()
        stars += getStar(x - 1, y - 1) + getStar(x - 1, y) + getStar(x - 1, y + 1) + getStar(x, y - 1) +
                getStar(x, y + 1) + getStar(x + 1, y - 1) + getStar(x + 1, y) + getStar(x + 1, y + 1)
        return stars
    }

    private fun getStar(x: Int, y: Int): Set<Pair<Int, Int>> {
        val c = engineSchematic.getOrNull(y)?.getOrNull(x) ?: return emptySet()
        return if (c == '*') setOf(x to y) else emptySet()
    }

    private fun checkNeighbors(x: Int, y: Int): Boolean =
        isSymbol(x - 1, y - 1) || isSymbol(x - 1, y) || isSymbol(x - 1, y + 1) || isSymbol(x, y - 1)
                || isSymbol(x, y + 1) || isSymbol(x + 1, y - 1) || isSymbol(x + 1, y) || isSymbol(x + 1, y + 1)

    private fun isSymbol(x: Int, y: Int): Boolean {
        val c = engineSchematic.getOrNull(y)?.getOrNull(x) ?: return false
        return !c.isDigit() && (c != '.')
    }
}

fun main() {
    val day03 = Day03(Day03::class.java.getResource(Day03.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day03::part1 -> ${day03.part1()}")
    println("Day03::part2 -> ${day03.part2()}")
}
