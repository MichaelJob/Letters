package ch.michaeljob.letters

import android.content.Context
import android.speech.tts.TextToSpeech
import org.koin.java.KoinJavaComponent.getKoin
import java.util.Locale

class AndroidTtsManager(context: Context) : TtsManager, TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = TextToSpeech(context, this)
    private var isReady = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.GERMAN)
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


actual fun createTtsManager(): TtsManager {
    return AndroidTtsManager(context=getKoin().get())
}
