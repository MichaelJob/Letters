package ch.michaeljob.letters

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    val ttsManager = remember { createTtsManager() }
    App(ttsManager)
}