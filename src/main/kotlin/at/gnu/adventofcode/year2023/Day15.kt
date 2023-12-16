package at.gnu.adventofcode.year2023

class Day15(private val initializationSequence: List<String>) {

    fun part1(): Int =
        initializationSequence.sumOf(::calculateHashcode)

    fun part2(): Int {
        val hashmap = mutableMapOf<Int, MutableList<Pair<String, Int>>>()
        for (step in initializationSequence) {
            val parts = step.split("=", "-")
            val operation = parts[0]
            val boxLabel = calculateHashcode(operation)
            val box = hashmap[boxLabel]
            if (step.contains("-"))
                box?.removeIf { it.first == operation }
            else {
                val lens = operation to parts[1].toInt()
                if (box == null)
                    hashmap[boxLabel] = mutableListOf(lens)
                else {
                    val lensIndex = box.indexOfFirst { it.first == operation }
                    if (lensIndex < 0)
                        box += lens
                    else
                        box[lensIndex] = lens
                }
            }
        }
//        println(hashmap)
        var focusingPower = 0
        for (i in hashmap.keys)
            for ((j, lens) in hashmap[i]!!.withIndex())
                focusingPower += (i + 1) * (j + 1) * lens.second
        return focusingPower
    }

    private fun calculateHashcode(step: String): Int =
        step.fold(0) { acc, c -> ((acc + c.code) * 17) % 256 }

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day15.txt"
    }
}

fun main() {
    val day15 = Day15(Day15::class.java.getResource(Day15.RESOURCE)!!.readText().trim().split(","))
    println("Day15::part1 -> ${day15.part1()}")
    println("Day15::part2 -> ${day15.part2()}")
}
