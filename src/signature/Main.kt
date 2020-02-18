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
        val nm = Array(10) { StringBuilder() } // name
        val st = Array(3) { StringBuilder() } // status

        convertString(name, "src/signature/fonts/roman.txt", nm)
        removeFirstLastDigit(2, nm) // remove spaces to ease positioning
        for (char in status) {
            if (char.isLetter()) {
                appendBuiltinLetter(char.toLowerCase(), st)
                addEnd(" ", 1, st)
            } else if (char == ' ') {
                addEnd(" ", 5, st)
            }
        }
        for (sb in st) sb.deleteCharAt(sb.lastIndex)

        if (nm[0].length > st[0].length) {
            adjustLength(nm, st)

        } else if (nm[0].length < st[0].length) {
            adjustLength(st, nm)
        } // else if nm[0].length == st[0].length  ignore, just add spaces/border

        return createOutput(nm, st, "*")
    }

    private fun convertString(str: String, fontPath: String, data: Array<StringBuilder>) {
        for (char in str) {
            if (char.isLetter()) {
                appendCharFromFile(fontPath, char, data)
            } else if (char == ' ') {
                addEnd(" ", 10, data)
            }
        }
        addEnd(" ", 1, data)
        addBgn(" ", 2, data)
    }

    private fun adjustLength(one: Array<StringBuilder>, two: Array<StringBuilder>) {
        val pos = calculatePositions(one[0].length, two[0].length)
        val line = " ".repeat(one[0].length)
        for (i in two.indices) {
            two[i] = StringBuilder(line.replaceRange(pos[0], pos[1], two[i]))
        }
    }

    private fun createOutput(nm: Array<StringBuilder>, st: Array<StringBuilder>, borderElem: String): Array<String> {
        addBgn("$borderElem  ", 1, st)
        addEnd("  $borderElem", 1, st)
        addBgn("$borderElem  ", 1, nm)
        addEnd("  $borderElem", 1, nm)
        val verticalLine = borderElem.repeat(nm[0].length).substring(0..nm[0].lastIndex)

        val output = Array(nm.size + st.size + 2) { "" }
        output[0] = verticalLine
        for (row in nm.indices) output[row + 1] = nm[row].toString()
        for (row in st.indices) output[row + 1 + nm.size] = st[row].toString()
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
