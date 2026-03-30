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

    var currentDice by mutableStateOf(Dice.entries.random())

    val ttsManager = createTtsManager()

    init {
        reset()
    }

    fun pickRandomLetter(letter: String) {
        if (remainingLetters.isNotEmpty()) {
            updateCurrentLetter(letter)
            remainingLetters.remove(letter)
            history.add(letter)
        }
    }

    fun onDiceSelected(){
        updateCurrentLetter(currentDice.value.toString())
        history.add(currentDice.value.toString())
    }

    fun reset() {
        remainingLetters = if (isNumbers) {
            allNumbers.value.shuffled().toMutableStateList()
        } else {
            allLetters.value.shuffled().toMutableStateList()
        }
        history.clear()
        currentLetter = ""
    }

    private fun updateCurrentLetter(letter: String){
        currentLetter = letter
        if (currentLetter != "") {
            ttsManager.speak(currentLetter)
        }
    }

    fun setIsNumbers(bool: Boolean) {
        isNumbers = bool
        reset()
    }

    fun rollTheDice() {
        currentDice = Dice.entries.random()
    }
}
