import java.io.File

val file = File("./input.txt")

val text = file.readText()
val blocks = text.split("\n\n")

val calories = blocks.map { it.split("\n") }

val sums = calories.map { it.map { it.toInt() }.sum() }
val sortedSums = sums.sortedDescending()

println("The elf with the most calories is carrying ${sortedSums.take(1).sum()} calories.")
println("The three elfs with the most calories are together carrying ${sortedSums.take(3).sum()} calories.")
