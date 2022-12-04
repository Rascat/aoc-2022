import java.io.File

val file = File("./input.txt")

typealias Range = Pair<Int, Int>

fun lineToRanges(line: String): Pair<Range, Range> {
    val (first, second) = line.split(",")
    val (firstFrom, firstTo) = first.split("-")
    val (secondFrom, secondTo) = second.split("-")
    return Pair(Pair(firstFrom.toInt(), firstTo.toInt()), Pair(secondFrom.toInt(), secondTo.toInt()))
}

fun doFullyOverlap(rangeA: Range, rangeB: Range): Boolean {
    if (rangeA.first <= rangeB.first && rangeA.second >= rangeB.second) {
        return true
    }

    if (rangeB.first <= rangeA.first && rangeB.second >= rangeA.second) {
        return true
    }

    return false
}

fun doPartiallyOverlap(rangeA: Range, rangeB: Range): Boolean {
    if ((rangeA.first <= rangeB.first && rangeA.second >= rangeB.first)
        || (rangeA.first >= rangeB.first && rangeA.first <= rangeB.second)) {
        return true
    }

    return false

}

val lines = file.readLines()
val resultPartOne = lines.map(::lineToRanges).count { doFullyOverlap(it.first, it.second) }
val resultPartTwo = lines.map(::lineToRanges).count { doPartiallyOverlap(it.first, it.second)}
println("In total, $resultPartOne ranges fully contain another")
println("In total, $resultPartTwo ranges partially contain another")
