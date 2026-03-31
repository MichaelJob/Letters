package ch.michaeljob.letters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class LetterViewModel : ViewModel() {
    var allLetters = mutableStateOf(('A'..'Z').map { it.toString() })
    var allNumbers = mutableStateOf(('1'..'9').map { it.toString() })
    var remainingLetters = mutableStateListOf<String>()
    var history = mutableStateListOf<String>()

    var currentLetter by mutableStateOf("")
        private set

    var isNumbers by mutableStateOf(false)
    var isDice by mutableStateOf(true)

    var isSpinning by mutableStateOf(false)

    var isMuted by mutableStateOf(false)

    var currentDice by mutableStateOf(Dice.entries.random())

    val ttsManager = createTtsManager()

    init {
        reset()
    }

    fun setIsNumbers(bool: Boolean) {
        isNumbers = bool
        reset()
    }

    fun reset() {
        remainingLetters = if (isNumbers) {
            allNumbers.value.shuffled().toMutableStateList()
        } else {
            allLetters.value.shuffled().toMutableStateList()
        }
        history.clear()
        currentLetter = ""
        isSpinning = false
    }


    //before animation
    fun spinTheWheel() {
        isSpinning = true
        currentLetter = remainingLetters.first()
    }

    //after animation
    fun updateCurrentLetter() {
        remainingLetters.remove(currentLetter)
        speakAfterSpinningUpdateHistory()
    }


    //before animation
    fun rollTheDice() {
        isSpinning = true
        currentDice = Dice.entries.random()
    }

    //after animation
    fun onDiceSelected() {
        currentLetter = currentDice.value.toString()
        speakAfterSpinningUpdateHistory()
    }

    private fun speakAfterSpinningUpdateHistory(){
        isSpinning = false
        history.add(currentLetter)
        if (!isMuted) ttsManager.speak(currentLetter)
    }

}
