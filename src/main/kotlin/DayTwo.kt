import java.io.BufferedReader
import java.io.File

const val OPPONENT_ROCK = "A"
const val OPPONENT_PAPER = "B"
const val OPPONENT_SCISSORS = "C"

const val PLAYER_ROCK = "X"
const val PLAYER_PAPER = "Y"
const val PLAYER_SCISSORS = "Z"

const val PLAYER_LOSE = "X"
const val PLAYER_DRAW = "Y"
const val PLAYER_WIN = "Z"

const val DRAW_SCORE = 3
const val PLAYER_WIN_SCORE = 6
const val OPPONENT_WIN_SCORE = 0

const val ROCK_VALUE = 1
const val PAPER_VALUE = 2
const val SCISSORS_VALUE = 3

private fun getChoiceScore(choice: String): Int {
    return when (choice) {
        PLAYER_ROCK -> ROCK_VALUE
        PLAYER_PAPER -> PAPER_VALUE
        PLAYER_SCISSORS -> SCISSORS_VALUE
        else -> 0
    }
}

private fun isChoiceSame(choice: String, opponentChoice: String): Boolean {
    return ((choice == PLAYER_ROCK && opponentChoice == OPPONENT_ROCK)
            || (choice == PLAYER_PAPER && opponentChoice == OPPONENT_PAPER)
            || (choice == PLAYER_SCISSORS && opponentChoice == OPPONENT_SCISSORS))
}

private fun partTwoGame(opponentChoice: String, desiredOutcome: String): Int {
    var playerChoice = ""

    if (opponentChoice == OPPONENT_ROCK && desiredOutcome == PLAYER_WIN
        || opponentChoice == OPPONENT_PAPER && desiredOutcome == PLAYER_DRAW
        || opponentChoice == OPPONENT_SCISSORS && desiredOutcome == PLAYER_LOSE
    ) {
        playerChoice = PLAYER_PAPER
    } else if (
        opponentChoice == OPPONENT_ROCK && desiredOutcome == PLAYER_DRAW
        || opponentChoice == OPPONENT_PAPER && desiredOutcome == PLAYER_LOSE
        || opponentChoice == OPPONENT_SCISSORS && desiredOutcome == PLAYER_WIN
    ) {
        playerChoice = PLAYER_ROCK
    } else if (
        opponentChoice == OPPONENT_ROCK && desiredOutcome == PLAYER_LOSE
        || opponentChoice == OPPONENT_PAPER && desiredOutcome == PLAYER_WIN
        || opponentChoice == OPPONENT_SCISSORS && desiredOutcome == PLAYER_DRAW
    ) {
        playerChoice = PLAYER_SCISSORS
    }


    return rockPaperScissors(playerChoice, opponentChoice)

}

private fun rockPaperScissors(
    playerChoice: String, opponentChoice: String
): Int {
    var score = 0

    if (isChoiceSame(playerChoice, opponentChoice)) {
        score = DRAW_SCORE
    } else if (playerChoice == PLAYER_ROCK && opponentChoice == OPPONENT_PAPER) {
        score = OPPONENT_WIN_SCORE
    } else if (playerChoice == PLAYER_ROCK && opponentChoice == OPPONENT_SCISSORS) {
        score = PLAYER_WIN_SCORE
    } else if (playerChoice == PLAYER_PAPER && opponentChoice == OPPONENT_ROCK) {
        score = PLAYER_WIN_SCORE
    } else if (playerChoice == PLAYER_PAPER && opponentChoice == OPPONENT_SCISSORS) {
        score = OPPONENT_WIN_SCORE
    } else if (playerChoice == PLAYER_SCISSORS && opponentChoice == OPPONENT_ROCK) {
        score = OPPONENT_WIN_SCORE
    } else if (playerChoice == PLAYER_SCISSORS && opponentChoice == OPPONENT_PAPER) {
        score = PLAYER_WIN_SCORE
    }

    return score + getChoiceScore(playerChoice)
}

private fun parseInput(): List<List<String>> {
    val bufferedReader: BufferedReader = File("inputs/day2.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    return inputString.split("\n").map { it.split(" ") }
}


private fun partOne(): Int {
    val rounds = parseInput()

    val results = rounds.map {
        rockPaperScissors(it[1], it[0])
    }


    val totalScore = results.sum()

    println("Total score assuming XYZ: $totalScore \n")
    return totalScore
}


private fun partTwo(): Int {
    val rounds = parseInput()

    val results = rounds.map {
        partTwoGame(it[0], it[1])
    }

    val totalScore = results.sum()

    println("Total score using the secret method: $totalScore \n")

    return totalScore


}

fun dayTwo() {
    partOne()
    partTwo()
}