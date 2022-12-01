import java.io.File

val file = File("./input.txt")

val text = file.readText()
val blocks = text.split("\n\n")

val calories = blocks.map { it.split("\n") }

val sums = calories.map { it.map { it.toInt() }.sum() }
println(sums.max())
