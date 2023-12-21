package at.gnu.adventofcode.year2023

class Day08(private val instructions: String, network: List<String>) {

    data class Node(val name: String, val left: String, val right: String)

    private val nodes = network.associate {
        val (name, left, right) = NODE.matchEntire(it)!!.destructured
        name to Node(name, left, right)
    }


    fun part1(): Long =
        calculateStepsFrom(nodes["AAA"]!!)

    fun part2(): Long {
        val startNodes = nodes.filter { it.key.last() == 'A' }.values
        val distances = startNodes.map { calculateStepsFrom(it, "Z") }
        return distances.fold(1L) { acc, it -> lcm(acc, it) }
    }

    private fun calculateStepsFrom(startNode: Node, endNode: String = "ZZZ"): Long {
        var currentNode = startNode
        var instruction = 0
        while (!currentNode.name.endsWith(endNode)) {
            currentNode = if (instructions[instruction++ % instructions.length] == 'L')
                nodes[currentNode.left]!!
            else
                nodes[currentNode.right]!!
        }
        return instruction.toLong()
    }

    private tailrec fun gcd(x: Long, y: Long): Long =
        if (x == 0L) y else gcd(y % x, x)

    private fun lcm(x: Long, y: Long): Long =
        x * (y / gcd(x, y))

    companion object {
        const val RESOURCE = "/adventofcode/year2023/Day08.txt"
        val NODE = """(\w+) = \((\w+), (\w+)\)""".toRegex()
    }
}

fun main() {
    val input = Day08::class.java.getResource(Day08.RESOURCE)!!.readText().trim().split("\n\n", "\r\n\r\n")
        .map { it.split("\n", "\r\n") }
    val day08 = Day08(input.first().first(), input.drop(1).first())
    println("Day08::part1 -> ${day08.part1()}")
    println("Day08::part2 -> ${day08.part2()}")
}
