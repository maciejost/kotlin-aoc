import java.io.BufferedReader
import java.io.File

fun dayFive() {
  parseCrates()
  parseInstructions()
}

data class Instruction(val from: Int, val to: Int, val amount: Int)

private fun parseInstructions(): List<Instruction> {
  val instructionsInput: BufferedReader = File("inputs/day5_instructions.txt").bufferedReader()
  val instructionsInputString = instructionsInput.use { it.readText() }

  val instructions = instructionsInputString.split("\n")

  /*move 1 from 2 to 1*/
  val instructionsAsObjects =
      instructions.map {
        val split = it.split(" ")

        val amount = split[1].toInt()
        val from = split[3].toInt()
        val to = split[5].toInt()

        Instruction(from, to, amount)
      }

  println("Instructions input as objects:\n\n$instructionsAsObjects")

  return instructionsAsObjects
}

const val EMPTY_CRATE_SPACE = "    "

data class Crate(val name: String, val creates: List<String>)

private fun parseCrates() {

  val cratesInput: BufferedReader = File("inputs/day5_crates.txt").bufferedReader()
  val cratesInputString = cratesInput.use { it.readText() }
    //remove last line
  val cratesAsLines = cratesInputString.split("\n").dropLast(1)

  val rowNumbers = cratesAsLines[cratesAsLines.size - 1]

    val chunkedCrates = cratesAsLines.map { it.chunked(4)}
    println(chunkedCrates)


  println("Row numbers: $rowNumbers")

  /*
          [D]
      [N] [C]
      [Z] [M] [P]
       1   2   3

       [{
           name: 1,
           creates: [Z, N]
       },
       {
          name: 2,
          creates: [M, C, D]
       }

       ]

  * */

}
