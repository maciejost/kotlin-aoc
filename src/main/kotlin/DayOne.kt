import java.io.BufferedReader
import java.io.File

private fun parseElfData(): List<Int> {
  val bufferedReader: BufferedReader = File("inputs/day1.txt").bufferedReader()
  val inputString = bufferedReader.use { it.readText() }

  val elves = inputString.split("\n\n").map { it.split("\n") }
  val washedValueElves =
      elves.map { it ->
        it.map {
          if (it == "" || it == "\n") {
            0
          } else {
            it.toInt()
          }
        }
      }

  return washedValueElves.map { it.sum() }
}

private interface Elf {
  val calories: Int
  val index: Int
}

private fun getHighestCalories(): Elf {
  val elves = parseElfData()
  val highestElf = elves.maxOrNull()

  return object : Elf {
    override val calories: Int
      get() = highestElf!!

    override val index: Int
      get() = elves.indexOf(highestElf)
  }
}

private fun getTopThree(): List<Elf> {
  val elves = parseElfData()
  val sortedElves = elves.sortedDescending()
  val topThree = sortedElves.take(3)
  val topThreeElves =
      topThree.map {
        val elfObject =
            object : Elf {
              override val calories: Int
                get() = it

              override val index: Int
                get() = elves.indexOf(it)
            }
        elfObject
      }

  return topThreeElves
}

fun dayOne() {
  println("Day One \n")
  val highestElf = getHighestCalories()
  val topThreeElves = getTopThree()

  val topThreeSum = topThreeElves.sumOf { it.calories }

  println("Top three elves:")
  topThreeElves.forEach { println("${it.index}, ${it.calories}") }
  println()

  println("Top elf: ${highestElf.index}, ${highestElf.calories}\n")

  println("Sum of top three: $topThreeSum")
}
