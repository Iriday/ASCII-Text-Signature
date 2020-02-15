package signature

fun main() {
    Main().run()
}

class Main {
    private val sbTop = StringBuilder()
    private val sbMid = StringBuilder()
    private val sbBtm = StringBuilder()

    fun run() {
        println("Enter name and surname: ")
        val input = readLine()!!.trim()

        for (value in input) {
            if (value.isLetter()) {
                appendLetter(value.toLowerCase())
                sbTop.append(' ')
                sbMid.append(' ')
                sbBtm.append(' ')
            } else if (value == ' ') {
                sbTop.append("     ")
                sbMid.append("     ")
                sbBtm.append("     ")
            } else {
                sbTop.append(' ')
                sbMid.append(' ')
                sbBtm.append(' ')
            }
        }

        val verticalLine = createVerticalLine(sbTop.length + 5, "*")

        println(verticalLine)
        println("*  $sbTop *")
        println("*  $sbMid *")
        println("*  $sbBtm *")
        println(verticalLine)
    }

    private fun appendLetter(letter: Char) {
        var counter = 0
        for (c in 'a'..'z') {
            if (c == letter) {
                sbTop.append(arrayTop[counter])
                sbMid.append(arrayMid[counter])
                sbBtm.append(arrayBtm[counter])
                return
            }
            counter++
        }
    }
}

private fun createVerticalLine(length: Int, element: String): String {
    val line = StringBuilder()
    repeat(length) {
        line.append(element)
    }
    return line.toString()
}
