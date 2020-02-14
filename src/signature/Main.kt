package signature

fun main() {
    val input = readLine()!!.trim()

    val verticalLine = createVerticalLine(input.length + 4, "*")

    println(verticalLine)
    println("* $input *")
    println(verticalLine)
}

private fun createVerticalLine(times: Int, element: String): String {
    val line = StringBuilder()
    repeat(times) {
        line.append(element)
    }
    return line.toString()
}
