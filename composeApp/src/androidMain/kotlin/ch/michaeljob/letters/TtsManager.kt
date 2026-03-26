package ch.michaeljob.letters

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class AndroidTtsManager(context: Context) : TtsManager, TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = TextToSpeech(context, this)
    private var isReady = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                isReady = true
            }
        }
    }

    override fun speak(text: String) {
        if (isReady) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}

// This is a bit tricky since we need Context. 
// In a real app, we'd use a DI or a CompositionLocal.
// For now, we'll provide a placeholder or use a global context if available.
actual fun createTtsManager(): TtsManager {
    throw RuntimeException("AndroidTtsManager needs a context. Use the direct constructor in Android code.")
}
