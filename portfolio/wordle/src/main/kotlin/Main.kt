
fun main() {
    val wordList = readWordList("data/words.txt") // reads file
    val chosenWord = pickRandomWord(wordList) // random word is picked
    val numAttempts = 10 // number of attempts allowed
    var isSolved = false // flag to track if the word is guessed

    for (attempt in 1..numAttempts) { // loop for each attempt
        val guess = obtainGuess(attempt)
        val result = evaluateGuess(guess, chosenWord)
        displayGuess(guess, result)
        if (result.all { it == 1 }) { // check if word is guessed
            println("You guessed the word!") // message if guessed
            isSolved = true
            break
        }
    }

    if (!isSolved) {
        println("Game Over! The word was $chosenWord") // message if not guessed
    }
}
