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
        val data = Array(10) { StringBuilder() }
        var status = status

        convertString(name, "src/signature/fonts/roman.txt", data)
        removeFirstLastDigit(2, data) // remove spaces to ease positioning

        if (data[0].length > status.length) {
            val pos = calculatePositions(data[0].length, status.length)
            status = " ".repeat(data[0].length).replaceRange(pos[0], pos[1], status)
        } else if (data[0].length < status.length) {
            val pos = calculatePositions(status.length, data[0].length)
            val line = " ".repeat(status.length)
            for (i in data.indices) {
                data[i] = StringBuilder(line.replaceRange(pos[0], pos[1], data[i]))
            }
        } // else if data[0].length == status.length  ignore, just add spaces/border
        status = "*  $status  *"
        addBgn("*  ", 1, data)
        addEnd("  *", 1, data)

        val verticalLine = createVerticalLine(status.length, "*")

        val output = Array(data.size + 3) { "" }
        output[0] = verticalLine
        for (i in data.indices) output[i + 1] = data[i].toString()
        output[output.lastIndex - 1] = status
        output[output.lastIndex] = verticalLine

        return output
    }

    private fun convertString(str: String, fontPath: String, data: Array<StringBuilder>) {
        for (char in str) {
            if (char.isLetter()) {
                appendCharFromFile(fontPath, char, data)
            } else if (char == ' ') {
                addEnd(" ", 5, data)
            }
        }
        addEnd(" ", 1, data)
        addBgn(" ", 2, data)
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

private fun removeFirstLastDigit(howMany: Int, data: Array<StringBuilder>) {
    for (sb in data) {
        sb.delete(sb.length - howMany, sb.length)
        sb.delete(0, howMany)
    }
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
