import java.io.BufferedReader
import java.io.File

private fun readInput (): String {
    val input: BufferedReader = File("inputs/day6.txt").bufferedReader()
    val inputAsString = input.readLine()

    return inputAsString

}


private fun areCharsUnique ( chars: List<Char> ): Boolean {
    val uniqueChars = chars.distinct()

    return uniqueChars.size == chars.size
}




private fun readPacket (): Int {
    val input = readInput()


    for (i in 0 until input.length - 4) {
        val packet = input.substring(i, i + 4)
        val chars = packet.toCharArray().toList()

        if (areCharsUnique(chars)) {
            return i + 4
        }
    }

    return 0

}

fun daySix() {
    val input = readInput()
    val packetIndex = readPacket()

    println("First window of four unique characters occurs at ${input.substring(packetIndex - 4, packetIndex)}, with the sequence \"${input.substring(packetIndex - 4, packetIndex)}\"")
}