package at.gnu.adventofcode.algorithms

import java.util.*
import kotlin.math.sqrt

class AStarAlgorithm(private val field: List<List<Node>>) {

    companion object {
        val outOfBounds = Node(-1, -1, Int.MAX_VALUE)
    }

    class Node(val x: Int, val y: Int, val cost: Int, var f: Double = 0.0, var g: Int = 0,
               var predecessor: Node? = null) : Comparable<Node> {

        override fun compareTo(other: Node): Int =
            this.f.compareTo(other.f)
    }

    private val openList = PriorityQueue<Node>()
    private val closedList = mutableSetOf<Node>()

    fun calculateCostOfCheapestPath(from: Node, to: Node): Int {
        openList += from
        while (openList.isNotEmpty()) {
            val currentNode = openList.poll()
            openList -= currentNode
            if (currentNode === to)
                return currentNode.g
            closedList += currentNode
            currentNode.expand(to)
        }
        return -1
    }

    private fun Node.expand(to: Node) {
        for (successor in neighbors()) {
            if (successor in closedList)
                continue
            val tentativeG = this.g + successor.cost
            if ((successor in openList) && (tentativeG >= successor.g))
                continue
            successor.predecessor = this
            successor.g = tentativeG
            successor.f = tentativeG + successor.h(to)
            if (successor !in openList)
                openList += successor
        }
    }

    private fun Node.neighbors(): Set<Node> =
        setOf(nodeAt(x, y + 1), nodeAt(x + 1, y), nodeAt(x - 1, y), nodeAt(x, y - 1))

    private fun nodeAt(x: Int, y: Int): Node =
        field.elementAtOrNull(y)?.elementAtOrNull(x) ?: outOfBounds

    private fun Node.h(to: Node): Double =
        sqrt((((this.x - to.x) * (this.x - to.x)) + ((this.y - to.y) * (this.y - to.y))).toDouble())
}

fun main() {
    val costs = listOf(
            "2413432311323",
            "3215453535623",
            "3255245654254",
            "3446585845452",
            "4546657867536",
            "1438598798454",
            "4457876987766",
            "3637877979653",
            "4654967986887",
            "4564679986453",
            "1224686865563",
            "2546548887735",
            "4322674655533")
    val field = costs.mapIndexed { y, row -> row.mapIndexed { x, c -> AStarAlgorithm.Node(x, y, c.digitToInt()) } }
    val from = field.first().first()
    val to = field.last().last()
    val minCost = AStarAlgorithm(field).calculateCostOfCheapestPath(from, to)
    if (minCost >= 0)
        println("The cheapest path from ${from.x}/${from.y} to ${to.x}/${to.y} costs $minCost")
    else
        println("Destination ${to.x}/${to.y} cannot be reached from source ${from.x}/${from.y}")

    field.visualizePath(to)
}

private fun List<List<AStarAlgorithm.Node>>.visualizePath(to: AStarAlgorithm.Node) {
    val smallNumbers = "₀₁₂₃₄₅₆₇₈₉"
    val nodes = mutableListOf(to)
    var current = to.predecessor
    while (current != null) {
        nodes.add(current)
        current = current.predecessor
    }
    for (y in this.indices) {
        for (x in this[y].indices) {
            val node = this[y][x]
            if (nodes.contains(node))
                print(node.cost)
            else
                print(smallNumbers[node.cost])
        }
        println("")
    }
}
