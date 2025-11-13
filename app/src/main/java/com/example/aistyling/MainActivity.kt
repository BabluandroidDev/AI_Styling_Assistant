package com.example.aistyling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aistyling.data.ChatRepository
import com.example.aistyling.ui.ChatScreen
import com.example.aistyling.ui.theme.AIStylingAssistantTheme
import com.example.aistyling.vm.ChatViewModel

class MainActivity : ComponentActivity() {

    private val vm: ChatViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ChatViewModel(ChatRepository(applicationContext)) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadInitial()
        setContent {
            AIStylingAssistantTheme {
                ChatScreen(viewModel = vm)
            }
        }
    }
}
