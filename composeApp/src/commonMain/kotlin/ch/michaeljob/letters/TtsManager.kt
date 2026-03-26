package ch.michaeljob.letters

interface TtsManager {
    fun speak(text: String)
    fun shutdown()
}

expect fun createTtsManager(): TtsManager
