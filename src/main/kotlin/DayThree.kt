import java.io.BufferedReader
import java.io.File

private fun hasMatch(currentChar: String, compartment: List<String>): Any {
  for (char in compartment) {
    if (currentChar == char) {
      return currentChar
    }
  }
  return false
}

private fun parseInput(): List<List<String>> {
  val bufferedReader: BufferedReader = File("inputs/day3.txt").bufferedReader()
  val inputString = bufferedReader.use { it.readText() }

  val splitByLine = inputString.split("\n").map { it.split("") }
  return splitByLine.map { it.subList(1, it.size - 1) }
}

private fun splitIntoSacks(): List<List<List<String>>> {
  val data = parseInput()

  return data.map { it.chunked(it.size / 2) }
}

private fun findMatches(): List<String> {
  val sacks = splitIntoSacks()

  val matches =
      sacks.map { sack ->
        val firstCompartment = sack[0]

        val matches =
            firstCompartment.map {
              val result = hasMatch(it, sack[1])

              if (result is String) {
                result
              } else {
                ""
              }
            }
        return@map matches.filter { it.isNotEmpty() }
      }

  return matches.map { it.first() }
}

private fun getPriority(item: Char): Int {
  val alphabets: MutableList<Char> = mutableListOf()

  for (a in 'a'..'z') {
    alphabets.add(a)
  }

  for (a in 'A'..'Z') {
    alphabets.add(a)
  }

  return alphabets.indexOf(item) + 1
}

private fun partOne() {
  val matches = findMatches()

  var result = 0

  matches.forEach { result += getPriority(it[0]) }

  println("Result of all priorities: $result \n")
}

private fun partTwo() {
  val data = parseInput()

  val groupedData = data.chunked(3)

  val common = groupedData.map { it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()) }

  val result = common.flatten().sumOf { getPriority(it[0]) }

  println("Result of all common identifiers : $result \n")
}

fun dayThree() {
  partOne()
  partTwo()
}
