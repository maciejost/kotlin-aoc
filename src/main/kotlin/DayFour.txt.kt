import java.io.BufferedReader
import java.io.File

private fun IntRange.fitsInside(other: IntRange): Boolean {
    return (this.first >= other.first && this.last <= other.last) || (this.first <= other.first && this.last >= other.last)
}

private fun parseInput(): List<List<String>> {
    val bufferedReader: BufferedReader = File("inputs/day4.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    val splitByLine = inputString.split("\n")
    val splitByPair = splitByLine.map { it.split(",") }

    return splitByPair
    println(splitByPair)
}


private fun partOne() {
    val pairs = parseInput()

    val pairsAsRanges = pairs.map { it.map { it.split("-") }}.map { it.map { it[0].toInt()..it[1].toInt() }}

    println(pairsAsRanges)

    var rangesThatFit= 0;

    for (i in 0 until pairsAsRanges.size) {
        val currentRange = pairsAsRanges[i][0]
        val nextRange = pairsAsRanges[i][1]

        if (currentRange.fitsInside(nextRange) || nextRange.fitsInside(currentRange)){
            rangesThatFit++
            println("Range $currentRange fits inside $nextRange")
        } else {
            println("Range $currentRange does not fit inside $nextRange")
        }
    }

    println(rangesThatFit)
}

fun dayFour() {
    partOne()

}