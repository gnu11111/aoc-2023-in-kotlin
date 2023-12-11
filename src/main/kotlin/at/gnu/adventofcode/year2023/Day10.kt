package at.gnu.adventofcode.year2023

class Day10(input: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day10.txt"
        val OUT_OF_BOUNDS = Position(-1, -1)
    }

    data class Position(val x: Int, val y: Int)
    data class Pipe(val symbol: Char, val position: Position, val conn1: Position, val conn2: Position)

    private val pipes = input.mapIndexedNotNull { y: Int, line: String ->
        line.mapIndexedNotNull { x, c ->
            when (c) {
                'S' -> Pipe('S', Position(x, y), OUT_OF_BOUNDS, OUT_OF_BOUNDS)
                '-' -> Pipe('-', Position(x, y), Position(x - 1, y), Position(x + 1, y))
                '|' -> Pipe('|', Position(x, y), Position(x, y - 1), Position(x, y + 1))
                'J' -> Pipe('J', Position(x, y), Position(x - 1, y), Position(x, y - 1))
                '7' -> Pipe('7', Position(x, y), Position(x, y + 1), Position(x - 1, y))
                'L' -> Pipe('L', Position(x, y), Position(x, y - 1), Position(x + 1, y))
                'F' -> Pipe('F', Position(x, y), Position(x + 1, y), Position(x, y + 1))
                else -> null
            }
        }.let { it.ifEmpty { null } }
    }.flatten()


    fun part1(): Int {
        val start = pipes.first { it.conn1 == OUT_OF_BOUNDS }
        val loops = findAllLoopsFrom(start)
        return (loops.maxOf { it.first.size } + 1) / 2
    }

    fun part2(): Int {
        val start = pipes.first { it.conn1 == OUT_OF_BOUNDS }
        val loop = findAllLoopsFrom(start).maxByOrNull { it.first.size }?.first ?: return -1
        val yMin = loop.minOf { it.position.y }
        val yMax = loop.maxOf { it.position.y }
        val xMin = loop.minOf { it.position.x }
        var count = 0
        for (y in yMin..yMax) {
            var inside = false
            var need7 = false
            var needJ = false
            for (x in xMin..loop.filter { it.position.y == y }.maxOf { it.position.x }) {
                val c = loop.firstOrNull { it.position.x == x && it.position.y == y }?.symbol ?: '.'
                when (c) {
                    '|' -> { inside = !inside; need7 = false; needJ = false }
                    'L' -> { need7 = true; needJ = false }
                    '7' -> { needJ = false; if (need7) inside = !inside }
                    'F' -> { needJ = true; need7 = false }
                    'J' -> { need7 = false; if (needJ) inside = !inside }
                    '.' -> { needJ = false; need7 = false; if (inside) count++ }
                }
//                print(if ((c == '.') && inside) '#' else c)
            }
//            println("")
        }
        return count
    }

    private fun findAllLoopsFrom(start: Pipe): MutableList<Pair<List<Pipe>, Boolean>> {
        val connectors = pipes.filter { it.conn1 == start.position || it.conn2 == start.position }
        if (connectors.size % 2 == 1)
            error("no loop!")
        val loops = mutableListOf<Pair<List<Pipe>, Boolean>>()
        val ends = mutableSetOf<Pipe>()
        for (connector in connectors) {
            if (connector in ends)
                continue
            val visited = calculateLoop(start, connector) ?: continue
            loops += visited
            ends += visited.first.last()
        }
        return loops
    }

    private fun calculateLoop(start: Pipe, next: Pipe): Pair<MutableList<Pipe>, Boolean>? {
        val visited = mutableListOf(start)
        var current = next
        var turnsLeft = 0
        var turnsRight = 0
        while (current != start) {
            val turns = ((current.conn1.x != current.conn2.x) && (current.conn1.y != current.conn2.y))
            if (current.conn1 == visited.last().position) {
                visited += current
                current = pipes.firstOrNull { it.position == current.conn2 } ?: return null
                if (turns) turnsLeft++
            } else {
                if (current in visited)
                    return null
                visited += current
                current = pipes.firstOrNull { it.position == current.conn1 } ?: return null
                if (turns) turnsRight++
            }
        }
        val firstX = visited[1].position.x - start.position.x
        val firstY = visited[1].position.y - start.position.y
        val lastX = visited.last().position.x - start.position.x
        val lastY = visited.last().position.y - start.position.y
        val newStart = when {
            (firstX < 0) && (lastY < 0) -> 'J'
            (firstX > 0) && (lastY < 0) -> 'L'
            (firstX < 0) && (lastY > 0) -> '7'
            (firstX > 0) && (lastY > 0) -> 'F'
            (firstY > 0) && (lastX > 0) -> 'F'
            (firstY > 0) && (lastX < 0) -> '7'
            (firstY < 0) && (lastX > 0) -> 'L'
            (firstY < 0) && (lastX < 0) -> 'J'
            else -> 'S'
        }
//        println("$firstX/$firstY, $lastX/$lastY -> $newStart")
        visited[0] = Pipe(newStart, start.position, start.conn1, start.conn2)
        return visited to (turnsRight > turnsLeft)
    }
}

fun main() {
    val day10 = Day10(Day10::class.java.getResource(Day10.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day10::part1 -> ${day10.part1()}")
    println("Day10::part2 -> ${day10.part2()}")
}
