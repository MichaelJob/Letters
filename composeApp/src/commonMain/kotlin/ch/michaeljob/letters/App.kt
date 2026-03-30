package ch.michaeljob.letters

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import ch.michaeljob.letters.ui.MainScreen
import ch.michaeljob.letters.ui.theme.LetterTheme

@Composable
fun App() {
    LetterTheme {
        MainScreen( viewModel { LetterViewModel() } )
    }

}