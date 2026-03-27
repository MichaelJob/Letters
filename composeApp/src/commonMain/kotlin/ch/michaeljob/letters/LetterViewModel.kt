package ch.michaeljob.letters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.emptyList

class LetterViewModel : ViewModel() {

    val allLetters = ('A'..'Z').map { it.toString() }
    val allNumbers = ('1'..'9').map { it.toString() }

    var remainingLetters = mutableStateListOf<String>()

    private val _history = MutableStateFlow<List<String>>(emptyList())
    val history: StateFlow<List<String>> = _history.asStateFlow()

    private val _currentLetter = MutableStateFlow<String?>(null)
    val currentLetter: StateFlow<String?> = _currentLetter.asStateFlow()

    private val _isSpinning = MutableStateFlow(false)
    val isSpinning: StateFlow<Boolean> = _isSpinning.asStateFlow()

    var isNumbers by mutableStateOf(false)

    fun pickRandomLetter() {
        if (_isSpinning.value) return
        
        val remaining = remainingLetters
        if (remaining.isNotEmpty()) {
            val nextLetter = remaining.first()
            _currentLetter.value = nextLetter
            remainingLetters.drop(1)
            _history.update { it + nextLetter }
        }
    }

    fun reset() {
        remainingLetters = allLetters.shuffled().toMutableStateList()
        _history.value = emptyList()
        _currentLetter.value = null
        _isSpinning.value = false
    }
    
    fun setSpinning(spinning: Boolean) {
        _isSpinning.value = spinning
    }

    fun setIsNumbers(bool: Boolean) {
        reset()
        isNumbers = bool
    }
}
