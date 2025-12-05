import java.io.File

fun isValid(word: String): Boolean {
    return word.length == 5 // checks if word is 5 letters long
}

fun readWordList(filename: String): MutableList<String> {
    val file = File(filename)
    return file.readLines().toMutableList() // reads lines into a mutable list
    // list is mutable so we can remove words later
}

fun pickRandomWord(words: MutableList<String>): String {
    val word = words.random()
    words.remove(word) // randomly picks a word and removes it from the list
    return word
}

fun obtainGuess(attempt: Int): String {
    while (true) {
        print("Attempt $attempt: ")
        val input = readlnOrNull() ?: "" // reads user input or empty string if null
        val guess = input.uppercase() // converts input to uppercase to match word list format
        if (isValid(guess)) { // checks if guess is valid
            return guess
        } else {
            println("Invalid guess, make sure your guess is 5 letters long, please try again.")
        }
    }
}

fun evaluateGuess(guess: String, theWord: String): List<Int> {
    val results = mutableListOf<Int>() // list to store results
    for (i in guess.indices) { // iterates through each letter of the guess
        if (guess[i] == theWord[i]) {
            results.add(1)
        } else {
            results.add(0)
        }
    }
    return results
}

fun displayGuess(guess: String, matches: List<Int>) {
    val stringBuilder = StringBuilder() //builds string to display the guess with matches
    for (i in guess.indices) {
        if (matches[i] == 1) {
            stringBuilder.append(guess[i])
        } else {
            stringBuilder.append("?")
        }
    }
    println(stringBuilder.toString())
}
