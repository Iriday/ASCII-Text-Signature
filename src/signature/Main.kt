package signature

fun main() {
    Main().run()
}

class Main {
    private val sbTop = StringBuilder()
    private val sbMid = StringBuilder()
    private val sbBtm = StringBuilder()

    fun run() {
        print("Enter name and surname: ")
        val input = readLine()!!.trim()
        print("Enter person's status: ")
        val status = readLine()!!.trim()

        for (value in input) {
            if (value.isLetter()) {
                appendLetter(value.toLowerCase())
                appendChar(' ', 1)
            } else if (value == ' ') {
                appendChar(' ', 5)
            } else {
                appendChar(' ', 1)
            }
        }
        appendChar(' ', 1)
        sbTop.insert(0, "  ")
        sbMid.insert(0, "  ")
        sbBtm.insert(0, "  ")

        var statusLine: String

        if (status.length <= sbTop.length - 2) {
            val sbLength = sbTop.length

            val pos = calculatePositions(sbLength, status.length)
            statusLine = " ".repeat(sbLength)
                .replaceRange(pos[0], pos[1], status)
        } else {
            statusLine = "  $status  "

            val offset = statusLine.length - sbTop.length
            val leftHalf = offset / 2

            val rightHalf = if (offset % 2 == 0) offset / 2 else offset / 2 + 1

            appendChar(' ', rightHalf)
            sbTop.insert(0, " ".repeat(leftHalf))
            sbMid.insert(0, " ".repeat(leftHalf))
            sbBtm.insert(0, " ".repeat(leftHalf))

            if (statusLine.length != sbTop.length) {
                statusLine = "$statusLine "
            }
        }
        val sp = if (statusLine[1] != ' ') "  " else if (statusLine[0] != ' ') " " else ""
        val verticalLine = createVerticalLine(sbTop.length + sp.length * 2 + 2, "*")

        println(verticalLine)
        println("*$sp$sbTop$sp*")
        println("*$sp$sbMid*$sp")
        println("$sp*$sbBtm*$sp")
        println("*$sp$statusLine*$sp")
        println(verticalLine)
    }

    private fun appendLetter(letter: Char) {
        for ((index, c) in ('a'..'z').withIndex()) {
            if (c == letter) {
                sbTop.append(arrayTop[index])
                sbMid.append(arrayMid[index])
                sbBtm.append(arrayBtm[index])
                return
            }
        }
    }

    private fun appendChar(char: Char, times: Int) {
        repeat(times) {
            sbTop.append(char)
            sbMid.append(char)
            sbBtm.append(char)
        }
    }
}

private fun calculatePositions(sbLength: Int, statusLength: Int): List<Int> {
    val startPos: Int
    val endPos: Int

    if (sbLength % 2 == 0 && statusLength % 2 == 1) {
        startPos = sbLength / 2 - statusLength / 2 - 1
    } else {
        startPos = sbLength / 2 - statusLength / 2
    }
    endPos = startPos + statusLength

    return listOf(startPos, endPos)
}

private fun createVerticalLine(length: Int, element: String): String {
    val line = StringBuilder()
    repeat(length) {
        line.append(element)
    }
    return line.toString()
}
