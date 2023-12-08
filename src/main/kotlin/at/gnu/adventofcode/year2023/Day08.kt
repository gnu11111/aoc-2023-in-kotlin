package at.gnu.adventofcode.year2023

class Day08(private val instructions: String, network: List<String>) {

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day08.txt"
        val NODE = """(\w+) = \((\w+), (\w+)\)""".toRegex()
    }

    data class Node(val name: String, val left: String, val right: String)

    private val nodes = network.map {
        val (_, name, left, right) = NODE.matchEntire(it)!!.groupValues
        Node(name, left, right)
    }

    fun part1(): Long =
        calculateStepsFrom(nodes.first { it.name == "AAA" })

    fun part2(): Long {
        val startNodes = nodes.filter { it.name.last() == 'A' }
        val results = startNodes.map { calculateStepsFrom(it, "Z") }
//        println(results)
        return results.fold(1L) { acc, it -> lcm(acc, it) }
    }

    private fun calculateStepsFrom(startNode: Node, endNode: String = "ZZZ"): Long {
        var currentNode = startNode
        var currentInstruction = 0
        while (!currentNode.name.endsWith(endNode)) {
            currentNode = if (instructions[currentInstruction++ % instructions.length] == 'L')
                nodes.first { it.name == currentNode.left }
            else
                nodes.first { it.name == currentNode.right }
        }
        return currentInstruction.toLong()
    }

    private tailrec fun gcd(x: Long, y: Long): Long =
        if (x == 0L) y else gcd(y % x, x)

    private fun lcm(x: Long, y: Long): Long =
        x * (y / gcd(x, y))
}

fun main() {
    val input = Day08::class.java.getResource(Day08.RESOURCE)!!.readText().trim().split("\n\n", "\r\n\r\n")
        .map { it.split("\n", "\r\n") }
    val day08 = Day08(input.first().first(), input.drop(1).first())
    println("Day08::part1 -> ${day08.part1()}")
    println("Day08::part2 -> ${day08.part2()}")
}
