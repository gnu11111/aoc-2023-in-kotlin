package at.gnu.adventofcode.year2023

class Day05(input: List<List<String>>) {

    data class Mapping(val range: LongRange, val offset: Long)

    private val seeds = input.first().first().split(" ").drop(1).map(String::toLong)

    private val allMappings = input.drop(1).map {
        it.drop(1).map { mapping ->
            val (destination, source, size) = mapping.trim().split(" ").map(String::toLong)
            Mapping(source until (source + size), destination - source)
        }
    }


    fun part1(): Long =
        seeds.map { seed ->
            var location = seed
            for (mappings in allMappings) {
                for (mapping in mappings) {
                    if (location in mapping.range) {
                        location += mapping.offset
                        break
                    }
                }
            }
            location
        }.minOf { it }

    fun part2(): Long {
        val seedRanges = seeds.chunked(2).map { it[0] until (it[0] + it[1]) }
        for (finalLocation in 0L..Int.MAX_VALUE) {
            var location = finalLocation
            for (mappings in allMappings.reversed())
                for (mapping in mappings)
                    if ((location - mapping.offset) in mapping.range) {
                        location -= mapping.offset
//                        println("$mapping: ${location + mapping.offset} -> $location")
                        break
                    }
            if (seedRanges.any { location in it })
                return finalLocation
        }
        return -1L
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day05.txt"
    }
}

fun main() {
    val day05 = Day05(Day05::class.java.getResource(Day05.RESOURCE)!!.readText().trim().split("\n\n", "\r\n\r\n")
        .map { it.split("\n", "\r\n") })
    println("Day05::part1 -> ${day05.part1()}")
    println("Day05::part2 -> ${day05.part2()}")
}
