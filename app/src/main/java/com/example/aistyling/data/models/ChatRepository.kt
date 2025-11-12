package com.example.aistyling.data


import android.content.Context
import com.example.aistyling.data.models.ChatMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlin.random.Random

class ChatRepository(private val context: Context) {

    private var responsesJson: JSONObject? = null

    private suspend fun loadJson() {
        if (responsesJson != null) return
        withContext(Dispatchers.IO) {
            val jsonStr = context.assets.open("responses.json").bufferedReader().use { it.readText() }
            responsesJson = JSONObject(jsonStr)
        }
    }

    suspend fun getAiReplyFor(key: String?): String {
        loadJson()
        val k = key?.lowercase()?.replace(" ", "_") ?: "default"
        return withContext(Dispatchers.Default) {
            val arr = responsesJson?.optJSONArray(k) ?: responsesJson?.optJSONArray("default")
            if (arr != null && arr.length() > 0) {
                arr.getString(Random.nextInt(0, arr.length()))
            } else {
                "Sorry, I don't have a suggestion for that right now."
            }
        }
    }

    // Simple helper to produce ChatMessage
    fun aiMessage(text: String) = ChatMessage(text = text, isUser = false)
    fun userMessage(text: String) = ChatMessage(text = text, isUser = true)
}
