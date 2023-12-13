package at.gnu.adventofcode.year2023

class Day13(private val notes: List<List<String>>) {

    fun part1(): Int =
        notes.sumOf(::calculateNoteValue)

    fun part2(): Int =
        notes.sumOf { calculateNoteValue(it, smudgesToFix = 1) }

    private fun calculateNoteValue(note: List<String>, smudgesToFix: Int = 0): Int {
        val length = note.first().length
        for (i in 1 until note.size) {
            var delta = 0
            for (j in 0 until i.coerceAtMost(note.size - i)) {
                delta += (0 until length).count { note[i + j][it] != note[i - j - 1][it] }
                if (delta > smudgesToFix)
                    break
            }
            if (delta == smudgesToFix)
                return 100 * i
        }
        for (i in 1 until length) {
            var delta = 0
            for (j in 0 until i.coerceAtMost(length - i)) {
                delta += note.count { it[i + j] != it[i - j - 1] }
                if (delta > smudgesToFix)
                    break
            }
            if (delta == smudgesToFix)
                return i
        }
        return 0
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day13.txt"
    }
}

fun main() {
    val day13 = Day13(Day13::class.java.getResource(Day13.RESOURCE)!!.readText().trim().split("\n\n", "\r\n\r\n")
        .map { it.split("\n", "\r\n") })
    println("Day13::part1 -> ${day13.part1()}")
    println("Day13::part2 -> ${day13.part2()}")
}
