import io.kotest.assertions.withClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

@Suppress("unused")
class WordleTest : StringSpec({
    "isValid returns true for 5 letter words" {
        isValid("AWDSA") shouldBe true // Random 5 letter word
        isValid("STONE") shouldBe true // English 5 letter word
    }

    "isValid returns false for non 5 letter words" {
        isValid("ADGW") shouldBe false  //4 letters are too little
        isValid("KLFFSX") shouldBe false //6 letters are too many
        isValid("") shouldBe false //Empty string is too little
    }

    "evaluateGuess returns 1s for complete match" {
        evaluateGuess("CRANE", "CRANE") shouldBe listOf(1, 1, 1, 1, 1) // All letters match
    }

    "evaluateGuess returns all 0s for no matches" {
        evaluateGuess("ASDFG", "QWERT") shouldBe listOf(0, 0, 0, 0, 0) // No letters match
    }

    "evaluateGuess returns scrambled 1s and 0s accordingly" {
        evaluateGuess("QWDFG", "QWERT") shouldBe listOf(1, 1, 0, 0, 0) // Matches at index 0 and 1
        evaluateGuess("VVQVV", "ZZQZZ") shouldBe listOf(0, 0, 1, 0, 0) // Match at index 2
    }

    "pickRandomWord returns a word from the list and removes it" {
        val words = mutableListOf("STUCK", "CRUDE", "POLKA")
        val ogSize = words.size
        val pick = pickRandomWord(words)
        val wasInList = pick == "STUCK" || pick == "CRUDE" || pick == "POLKA" // Check picked word was from original list
        wasInList shouldBe true  // Check picks are in list
        words.size shouldBe ogSize - 1 //Check size decreased by 1
        words.contains(pick) shouldBe false //Check picked word is removed
    }

    "readWordList reads lines from a file" {
        val tempFile = File.createTempFile("testwords", ".txt")
        tempFile.writeText("THIS\nIS\nTEST")
        val result = readWordList(tempFile.absolutePath)
        result.size shouldBe 3 //Checks size
        result.contains("THIS") shouldBe true
        result.contains("IS") shouldBe true
        result.contains("TEST") shouldBe true // Checks contents
        tempFile.deleteOnExit() // Clean up temp file
    }
})
