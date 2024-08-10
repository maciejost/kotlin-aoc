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




private fun readPacket (windowSize: Int): Int {
    val input = readInput()


    for (i in 0 until input.length - windowSize) {
        val packet = input.substring(i, i + windowSize)
        val chars = packet.toCharArray().toList()

        if (areCharsUnique(chars)) {
            return i + windowSize
        }
    }

    return 0

}



fun daySix() {
    val input = readInput()
    val packetIndex = readPacket(4)
    val messageIndex = readPacket(14)

    println("First window of four unique characters occurs at index ${packetIndex}, with the sequence \"${input.substring(packetIndex - 4, packetIndex)}\"")
    println("The first window of 14 unique characters occurs at index ${messageIndex}, with the sequence \"${input.substring(messageIndex - 14, messageIndex)}\"")
}