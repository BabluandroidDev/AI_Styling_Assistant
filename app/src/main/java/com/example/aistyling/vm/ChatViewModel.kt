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

    data class ChatSuggestion(
        val label: String,
        val key: String
    )

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val _isBotTyping = MutableStateFlow(false)
    val isBotTyping: StateFlow<Boolean> = _isBotTyping

    val suggestions = listOf(
        ChatSuggestion("Monsoon Make-Up Tips", "monsoon_makeup_tips"),
        ChatSuggestion("Need Booking Helps", "need_booking_helps"),
        ChatSuggestion("Quick Ready Tips", "quick_ready_tips"),
        ChatSuggestion("Get Ready for Birthday", "get_ready_for_birthday")
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

        viewModelScope.launch {
            _isBotTyping.value = true
            delay(700)
            val reply = repo.getAiReplyFor(suggestionKey ?: text)
            delay(400)
            val aiMsg = repo.aiMessage(reply)
            _messages.value = _messages.value + aiMsg
            _isBotTyping.value = false
        }
    }

    fun sendSuggestion(suggestion: ChatSuggestion) {
        sendUserMessage(suggestion.label, suggestion.key)
    }
}
