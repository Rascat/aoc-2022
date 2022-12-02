import java.io.File


/**
 * Move of the opponent in a game of Rock-Paper-Scissors.
 * 'A' := Rock
 * 'B' := Paper
 * 'C' := Scissors
 */
enum class OpponentMove { A, B, C }

/**
 * Own move in a game of Rock-Paper-Scissors.
 * 'X' := Rock
 * 'Y' := Paper
 * 'Z' := Scissors
 */
enum class OwnMove(val score: Int) { X(1), Y(2), Z(3) }

/**
 * The desired outcome of a round.
 * 'X' := Defeat
 * 'Y' := Draw
 * 'Z' := Win
 */
enum class Outcome(val score: Int) { X(0), Y(3), Z(6) }

val scoreDefeat = 0
val scoreDraw = 3
val scoreWin = 6

fun scoreOfRound1(round: Pair<OpponentMove, OwnMove>): Int {
    val (opponent, own) = round

    when (opponent) {
        // Opponent plays 'Rock'
        OpponentMove.A -> return when (own) {
            OwnMove.X -> scoreDraw + OwnMove.X.score
            OwnMove.Y -> scoreWin + OwnMove.Y.score
            OwnMove.Z -> scoreDefeat + OwnMove.Z.score
        }
        // Opponent plays 'Paper'
        OpponentMove.B -> return when (own) {
            OwnMove.X -> scoreDefeat + OwnMove.X.score
            OwnMove.Y -> scoreDraw + OwnMove.Y.score
            OwnMove.Z -> scoreWin + OwnMove.Z.score
        }
        // Opponent plays 'Scissors'
        OpponentMove.C -> return when (own) {
            OwnMove.X -> scoreWin + OwnMove.X.score
            OwnMove.Y -> scoreDefeat + OwnMove.Y.score
            OwnMove.Z -> scoreDraw + OwnMove.Z.score
        }
    }
}

fun scoreOfRound2(round: Pair<OpponentMove, Outcome>): Int {
    val (opponent, outcome) = round

    when (outcome) {
        Outcome.X -> return when (opponent) {
            OpponentMove.A -> OwnMove.Z.score + scoreDefeat
            OpponentMove.B -> OwnMove.X.score + scoreDefeat
            OpponentMove.C -> OwnMove.Y.score + scoreDefeat
        }

        Outcome.Y -> return when (opponent) {
            OpponentMove.A -> OwnMove.X.score + scoreDraw
            OpponentMove.B -> OwnMove.Y.score + scoreDraw
            OpponentMove.C -> OwnMove.Z.score + scoreDraw
        }

        Outcome.Z -> return when (opponent) {
            OpponentMove.A -> OwnMove.Y.score + scoreWin
            OpponentMove.B -> OwnMove.Z.score + scoreWin
            OpponentMove.C -> OwnMove.X.score + scoreWin
        }
    }
}

fun lineToRound1(line: String): Pair<OpponentMove, OwnMove> {
    val opponent = when (val char = line[0]) {
        'A' -> OpponentMove.A
        'B' -> OpponentMove.B
        'C' -> OpponentMove.C
        else -> {
            throw Error("Could not parse $char")
        }
    }
    val own = when (val char = line[2]) {
        'X' -> OwnMove.X
        'Y' -> OwnMove.Y
        'Z' -> OwnMove.Z
        else -> {
            throw Error("Could not parse $char")
        }
    }
    return Pair(opponent, own)
}

fun lineToRound2(line: String): Pair<OpponentMove, Outcome> {
    val opponent = when (val char = line[0]) {
        'A' -> OpponentMove.A
        'B' -> OpponentMove.B
        'C' -> OpponentMove.C
        else -> {
            throw Error("Could not parse $char")
        }
    }
    val own = when (val char = line[2]) {
        'X' -> Outcome.X
        'Y' -> Outcome.Y
        'Z' -> Outcome.Z
        else -> {
            throw Error("Could not parse $char")
        }
    }
    return Pair(opponent, own)
}

val file = File("./input.txt")
val lines = file.readLines()
val resultPartOne = lines.map(::lineToRound1).sumOf(::scoreOfRound1)
val resultPartTwo = lines.map(::lineToRound2).sumOf(::scoreOfRound2)

println("Result of part one is $resultPartOne")
println("Result of part two is $resultPartTwo")
