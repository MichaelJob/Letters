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
    var isWheelSpinning by mutableStateOf(false)
    var isNumbers by mutableStateOf(false)


    init {
        reset()
    }
    fun pickRandomLetter(letter: String) {
        if (isWheelSpinning) return

        if (remainingLetters.isNotEmpty()) {
            currentLetter = letter
            remainingLetters.remove(letter)
            history.add(letter)
        }
    }

    fun reset() {
        remainingLetters = if (isNumbers) {
            allNumbers.value.shuffled().toMutableStateList()
        } else {
            allLetters.value.shuffled().toMutableStateList()
        }
        history.clear()
        currentLetter = ""
        isWheelSpinning = false
    }

    fun setSpinning(spinning: Boolean) {
        isWheelSpinning = spinning
    }

    fun setIsNumbers(bool: Boolean) {
        isNumbers = bool
        reset()
    }
}
