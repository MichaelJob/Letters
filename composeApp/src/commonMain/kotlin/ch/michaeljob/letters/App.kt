package ch.michaeljob.letters

import androidx.compose.runtime.*
import ch.michaeljob.letters.theme.LetterTheme

@Composable
fun App(ttsManager: TtsManager) {
    LetterTheme {
        MainScreen(
            onLetterSelected = { letter ->
                ttsManager.speak(letter)
            }
        )
    }

}