package signature

fun main() {
    Main().run()
}

class Main {
    fun run() {
        val input = input()
        val signature = createSignature(input[0], input[1])
        output(signature)
    }

    private fun createSignature(name: String, status: String): Array<String> {
        val data = Array(3) { StringBuilder() }

        for (value in name) {
            if (value.isLetter()) {
                appendBuiltinLetter(value.toLowerCase(), data)
                addEnd(" ", 1, data)
            } else if (value == ' ') {
                addEnd(" ", 5, data)
            } else {
                addEnd(" ", 1, data)
            }
        }
        addEnd(" ", 1, data)
        addBgn(" ", 2, data)

        var statusLine: String

        if (status.length <= data[0].length - 2) {
            val sbLength = data[0].length
            val pos = calculatePositions(sbLength, status.length)

            statusLine = " ".repeat(sbLength)
                .replaceRange(pos[0], pos[1], status)
        } else {
            statusLine = "  $status  "

            val offset = statusLine.length - data[0].length
            val leftHalf = offset / 2
            val rightHalf = if (offset % 2 == 0) offset / 2 else offset / 2 + 1

            addBgn(" ", leftHalf, data)
            addEnd(" ", rightHalf, data)

            if (statusLine.length != data[0].length) {
                statusLine = "$statusLine "
            }
        }
        val spaces = if (statusLine[1] != ' ') 2 else if (statusLine[0] != ' ') 1 else 0
        val verticalLine = createVerticalLine(data[0].length + spaces * 2 + 2, "*")

        addBgn(" ", spaces, data)
        addEnd(" ", spaces, data)
        addBgn("*", 1, data)
        addEnd("*", 1, data)

        val output = Array(data.size + 3) { "" }
        output[0] = verticalLine
        for (i in data.indices) output[i + 1] = data[i].toString()
        val sp = " ".repeat(spaces)
        output[output.lastIndex - 1] = "*$sp$statusLine*$sp"
        output[output.lastIndex] = verticalLine

        return output
    }

    private fun input(): Array<String> {
        print("Enter name and surname: ")
        val input = readLine()!!.trim()
        print("Enter person's status: ")
        val status = readLine()!!.trim()
        return arrayOf(input, status)
    }

    private fun <T> output(data: Array<T>) {
        for (line in data) {
            println(line)
        }
    }
}

private fun addBgn(str: String, times: Int, data: Array<StringBuilder>): Array<StringBuilder> {
    val s = str.repeat(times)
    for (sb in data) {
        sb.insert(0, s)
    }
    return data
}

private fun addEnd(str: String, times: Int, data: Array<StringBuilder>): Array<StringBuilder> {
    val s = str.repeat(times)
    for (sb in data) {
        sb.append(s)
    }
    return data
}

private fun calculatePositions(sbLength: Int, statusLength: Int): List<Int> {
    val startPos = if (sbLength % 2 == 0 && statusLength % 2 == 1) {
        sbLength / 2 - statusLength / 2 - 1
    } else {
        sbLength / 2 - statusLength / 2
    }
    val endPos = startPos + statusLength

    return listOf(startPos, endPos)
}

private fun createVerticalLine(length: Int, element: String): String {
    return element.repeat(length)
}
