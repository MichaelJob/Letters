package ch.michaeljob.letters

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.AVSpeechBoundary
import platform.AVFAudio.AVSpeechSynthesisVoice
import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechUtterance
import platform.AVFAudio.setActive

@OptIn(ExperimentalForeignApi::class)
class IosTtsManager : TtsManager {
    private val synthesizer = AVSpeechSynthesizer()

    init {
        val session = AVAudioSession.sharedInstance()
        session.setCategory(AVAudioSessionCategoryPlayback, error = null)
        session.setActive(true, error = null)
    }

    override fun speak(text: String) {
        val utterance = AVSpeechUtterance(string = text.toLowerCase(Locale.current))
        utterance.voice = AVSpeechSynthesisVoice.voiceWithLanguage("de-CH")
        synthesizer.speakUtterance(utterance)
    }

    override fun shutdown() {
        if (synthesizer.isSpeaking()) {
            synthesizer.stopSpeakingAtBoundary(AVSpeechBoundary.AVSpeechBoundaryImmediate)
        }
    }
}

actual fun createTtsManager(): TtsManager = IosTtsManager()
