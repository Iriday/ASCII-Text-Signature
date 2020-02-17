package signature

import java.io.File
import java.util.*

val arrayTop = arrayOf("____","___ ","____","___ ", "____","____","____","_  _","_"," _","_  _", "_   ","_  _", "_  _", "____","___ ","____", "____", "____","___","_  _","_  _", "_ _ _","_  _", "_   _", "___ ")
val arrayMid = arrayOf("|__|","|__]","|   ","|  \\","|___","|___","| __","|__|","|"," |","|_/ ", "|   ","|\\/|","|\\ |","|  |","|__]","|  |", "|__/", "[__ "," | ","|  |","|  |", "| | |"," \\/ "," \\_/ ","  / ")
val arrayBtm = arrayOf("|  |","|__]","|___","|__/", "|___","|   ","|__]","|  |","|","_|","| \\_","|___","|  |" ,"| \\|","|__|","|   ","|_\\|","|  \\","___]"," | ","|__|"," \\/ ","|_|_|","_/\\_","  |  ", " /__")

fun appendBuiltinLetter(letter: Char, data: Array<StringBuilder>) {
    for ((index, c) in ('a'..'z').withIndex()) {
        if (c == letter) {
            data[0].append(arrayTop[index])
            data[1].append(arrayMid[index])
            data[2].append(arrayBtm[index])
            return
        }
    }
}

fun appendCharFromFile(filepath: String, char: Char, data: Array<StringBuilder>) {
    val scn = Scanner(File(filepath))
    val charHeight = scn.nextInt()
    val charNameAndWidth = Regex("$char [0-9]+")

    while (scn.hasNext()) {
        if (!scn.nextLine().matches(charNameAndWidth)) {
            continue
        }
        var index = 0
        repeat(charHeight) {
            data[index++].append(scn.nextLine())
        }
        return
    }
}
