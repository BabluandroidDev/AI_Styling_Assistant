package com.example.aistyling.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aistyling.data.ChatRepository
import com.example.aistyling.data.models.ChatMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(private val repo: ChatRepository) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val _isBotTyping = MutableStateFlow(false)
    val isBotTyping: StateFlow<Boolean> = _isBotTyping

    val suggestions = listOf(
        "Monsoon Make-Up Tips",
        "Need Booking Helps",
        "Quick Ready Tips",
        "Get Ready for Birthday"
    )

    fun loadInitial() {
        if (_messages.value.isEmpty()) {
            val intro = repo.aiMessage("Hello! I’m your AI Styling Assistant. How can I help today?")
            _messages.value = listOf(intro)
        }
    }

    fun sendUserMessage(text: String, suggestionKey: String? = null) {
        if (text.isBlank()) return
        val userMsg = repo.userMessage(text)
        _messages.value = _messages.value + userMsg

        // simulate bot typing & reply
        viewModelScope.launch {
            _isBotTyping.value = true
            delay(700) // small typing delay
            val reply = repo.getAiReplyFor(suggestionKey ?: text)
            delay(400) // emulate thinking
            val aiMsg = repo.aiMessage(reply)
            _messages.value = _messages.value + aiMsg
            _isBotTyping.value = false
        }
    }
}
