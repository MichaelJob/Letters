package ch.michaeljob.letters.theme

import androidx.compose.runtime.Composable

@Composable
actual fun LetterTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    // iOS doesn't support Material 3 dynamic color, so we always use the shared vibrant theme
    SharedTheme(darkTheme = darkTheme, content = content)
}
