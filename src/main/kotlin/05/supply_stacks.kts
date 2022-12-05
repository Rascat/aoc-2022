import java.io.File
import java.util.EmptyStackException
import java.util.Stack

/**
 * Type represents a move.
 * <Qty, From, To>
 */
typealias Move = Triple<Int, Int, Int>

val file = File("./input.txt")

val stacks = Array(9) { _ -> Stack<Char>() }

fun initStacks() {
    stacks.forEachIndexed() { i, stack ->
        if (i == 0) {
            stack.addAll(listOf('D', 'L', 'V', 'T', 'M', 'H', 'F'))
        }
        if (i == 1) {
            stack.addAll(listOf('H', 'Q', 'G', 'J', 'C', 'T', 'N', 'P'))
        }
        if (i == 2) {
            stack.addAll(listOf('R', 'S', 'D', 'M', 'P', 'H'))
        }
        if (i == 3) {
            stack.addAll(listOf('L', 'B', 'V', 'F'))
        }
        if (i == 4) {
            stack.addAll(listOf('N', 'H', 'G', 'L', 'Q'))
        }
        if (i == 5) {
            stack.addAll(listOf('W', 'B', 'D', 'G', 'R', 'M', 'P'))
        }
        if (i == 6) {
            stack.addAll(listOf('G', 'M', 'N', 'R', 'C', 'H', 'L', 'Q'))
        }
        if (i == 7) {
            stack.addAll(listOf('C', 'L', 'W'))
        }
        if (i == 8) {
            stack.addAll(listOf('R', 'D', 'L', 'Q', 'J', 'Z', 'M', 'T'))
        }
    }
}

fun lineToMove(line: String): Move {
    val segments = line.split(" ")
    val qty = segments[1].toInt()
    val from = segments[3].toInt()
    val to = segments[5].toInt()

    return Triple(qty, from, to)
}

fun applyMove9000(move: Move) {
    for (i in 1..move.first) {
        val crate = stacks[move.second - 1].pop()
        stacks[move.third - 1].push(crate)
    }
}

fun applyMove9001(move: Move) {
    val crates = mutableListOf<Char>()
    for (i in 1..move.first) {
        crates.add(stacks[move.second - 1].pop())
    }
    crates.reverse()
    stacks[move.third - 1].addAll(crates)
}

fun peekAtTopCrates(): String {
    var result = ""
    for (stack in stacks) {
        result += try {
            stack.peek()
        } catch (e: EmptyStackException) {
            '_'
        }
    }

    return result
}

val lines = file.readLines()
initStacks()
var moves = lines.slice(10 until lines.size).map { lineToMove(it) }
moves.forEach { it -> applyMove9001(it) }
val resultPartOne = peekAtTopCrates()
println("Top crates are: $resultPartOne")
