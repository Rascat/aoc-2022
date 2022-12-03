import java.io.File

typealias Rucksack = Pair<String, String>
typealias Group = Triple<String, String, String>

fun lineToRucksack(line: String): Rucksack {
    val firstCompartment = line.substring(0, line.length / 2)
    val secondCompartment = line.substring(line.length / 2)

    return Pair(firstCompartment, secondCompartment)
}

fun findDuplicateInRucksack(rucksack: Rucksack): Char {
    val valuesInFirst = rucksack.first.toSet()
    val valuesInSecond = rucksack.second.toSet()
    val intersection = valuesInFirst.intersect(valuesInSecond)

    if (intersection.size > 1) {
        throw Error("First and second compartment shared more than one element")
    }

    return intersection.first()
}

fun findDuplicateInGroup(group: Group): Char {
    val itemsInFirstRucksack = group.first.toSet()
    val itemsInSecondRucksack = group.second.toSet()
    val itemsInThirdRucksack = group.third.toSet()
    val intersection = itemsInFirstRucksack.intersect(itemsInSecondRucksack).intersect(itemsInThirdRucksack)

    if (intersection.size > 1) {
        throw Error("Group shares more than one item")
    }

    return intersection.first()
}

fun itemToPriority(item: Char): Int {
    val matchAToZLower = Regex("[a-z]")

    // We assume that if the char is not a lower-case letter, it MUST be an upper-case letter
    return if (matchAToZLower.matches(item.toString())) item.code - 96 else item.code - 38
}

val file = File("./input.txt")
val lines = file.readLines()

val solutionPartOne = lines.map(::lineToRucksack).map(::findDuplicateInRucksack).sumOf(::itemToPriority)
val solutionPartTwo = lines.chunked(3)
    .map { Triple(it[0], it[1], it[2]) }
    .map(::findDuplicateInGroup)
    .sumOf(::itemToPriority)

println("The sum of priorities of the item types in part one is: $solutionPartOne")
println("The sum of priorities of the item types in part two is: $solutionPartTwo")
