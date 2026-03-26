package ch.michaeljob.letter.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LetterViewModel : ViewModel() {

    private val allLetters = ('A'..'Z').map { it.toString() }

    private val _remainingLetters = MutableStateFlow(allLetters.shuffled())
    val remainingLetters: StateFlow<List<String>> = _remainingLetters.asStateFlow()

    private val _history = MutableStateFlow<List<String>>(emptyList())
    val history: StateFlow<List<String>> = _history.asStateFlow()

    private val _currentLetter = MutableStateFlow<String?>(null)
    val currentLetter: StateFlow<String?> = _currentLetter.asStateFlow()

    private val _isSpinning = MutableStateFlow(false)
    val isSpinning: StateFlow<Boolean> = _isSpinning.asStateFlow()

    fun pickRandomLetter() {
        if (_isSpinning.value) return
        
        val remaining = _remainingLetters.value
        if (remaining.isNotEmpty()) {
            val nextLetter = remaining.first()
            _currentLetter.value = nextLetter
            _remainingLetters.update { it.drop(1) }
            _history.update { it + nextLetter }
        }
    }

    fun reset() {
        _remainingLetters.value = allLetters.shuffled()
        _history.value = emptyList()
        _currentLetter.value = null
        _isSpinning.value = false
    }
    
    fun setSpinning(spinning: Boolean) {
        _isSpinning.value = spinning
    }
}
