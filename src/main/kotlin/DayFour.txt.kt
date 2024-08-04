import java.io.BufferedReader
import java.io.File

private fun IntRange.fitsInside(other: IntRange): Boolean {
  return (this.first >= other.first && this.last <= other.last) ||
      (this.first <= other.first && this.last >= other.last)
}

private fun IntRange.hasOverlap(other: IntRange): Boolean {
  return this.first in other || this.last in other
}

private fun parseInput(): List<List<IntRange>> {
  val bufferedReader: BufferedReader = File("inputs/day4.txt").bufferedReader()
  val inputString = bufferedReader.use { it.readText() }

  val splitByLine = inputString.split("\n")
  val splitByPair = splitByLine.map { it.split(",") }

  val pairsAsRanges =
      splitByPair
          .map { pair -> pair.map { it.split("-") } }
          .map { rangeNumbers -> rangeNumbers.map { it[0].toInt()..it[1].toInt() } }

  return pairsAsRanges
}

private fun partOne() {
  val pairs = parseInput()
  var rangesThatFit = 0

  for (i in pairs.indices) {
    val currentRange = pairs[i][0]
    val nextRange = pairs[i][1]

    if (currentRange.fitsInside(nextRange) || nextRange.fitsInside(currentRange)) {
      rangesThatFit++
    }
  }

  println("Data input has $rangesThatFit redundant ranges.")
}

private fun partTwo() {
  val pairs = parseInput()
  var rangesThatOverlap = 0

  for (i in pairs.indices) {
    val currentRange = pairs[i][0]

    val nextRange = pairs[i][1]

    if (currentRange.hasOverlap(nextRange) || nextRange.hasOverlap(currentRange)) {

      rangesThatOverlap++
    }
  }

  println("Data input has $rangesThatOverlap overlapping ranges.")
}

fun dayFour() {
  partOne()
  partTwo()
}
