package at.gnu.adventofcode.year2023

import kotlin.math.max

class Day16(private val layout: List<String>) {

    data class Beam(val x: Int = 0, val y: Int = 0, val dx: Int = 1, val dy: Int = 0)

    private val maxX = layout.maxOf { it.length } - 1
    private val maxY = layout.size - 1


    fun part1(): Int =
        energizedTiles().uniquePositions()

    fun part2(): Int =
        max(
            (0..maxX).maxOf { x-> max(
                energizedTiles(Beam(x, 0, 0, 1)).uniquePositions(),
                energizedTiles(Beam(x, maxY, 0, -1)).uniquePositions()
            ) },
            (0..maxY).maxOf {y -> max(
                energizedTiles(Beam(0, y, 1, 0)).uniquePositions(),
                energizedTiles(Beam(maxX, y, -1, 0)).uniquePositions()
            ) }
        )

    private fun energizedTiles(beam: Beam = Beam(), visited: Set<Beam> = emptySet(),
                               cache: MutableMap<Beam, Set<Beam>> = mutableMapOf()): Set<Beam> {
        cache[beam]?.let { return it }
        val tiles = mutableSetOf<Beam>()
        var x = beam.x
        var y = beam.y
        var dx = beam.dx
        var dy = beam.dy
        while ((Beam(x, y, dx, dy) !in visited) && (y >= 0) && (y <= maxY) && (x >= 0) && (x <= maxX)) {
            val c = layout[y][x]
            tiles += Beam(x, y, dx, dy)
            when {
                (c == '\\') -> {
                    if (dx == 1) { dx = 0; dy = 1 }
                    else if (dx == -1) { dx = 0; dy = -1 }
                    else if (dy == 1) { dx = 1; dy = 0 }
                    else if (dy == -1) { dx = -1; dy = 0 }
                }
                (c == '/') -> {
                    if (dx == 1) { dx = 0; dy = -1 }
                    else if (dx == -1) { dx = 0; dy = 1 }
                    else if (dy == 1) { dx = -1; dy = 0 }
                    else if (dy == -1) { dx = 1; dy = 0 }
                }
                ((c == '-') && (dy != 0)) -> { tiles += energizedTiles(Beam(x - 1, y, -1, 0), visited + tiles, cache) +
                        energizedTiles(Beam(x + 1, y, 1, 0), visited + tiles, cache); break }
                ((c == '|') && (dx != 0)) -> { tiles += energizedTiles(Beam(x, y - 1, 0, -1), visited + tiles, cache) +
                        energizedTiles(Beam(x, y + 1, 0, 1), visited + tiles, cache); break }
            }
            x += dx
            y += dy
        }
        return tiles.also { cache[beam] = it }
    }

    private fun Set<Beam>.uniquePositions(): Int =
        map { it.x to it.y }.toSet().size

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day16.txt"
    }
}

fun main() {
    val day16 = Day16(Day16::class.java.getResource(Day16.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day16::part1 -> ${day16.part1()}")
    println("Day16::part2 -> ${day16.part2()}")
}
