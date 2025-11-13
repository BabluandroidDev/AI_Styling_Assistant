# AI Styling Assistant

A modern Android chat application that provides AI-powered styling tips for makeup, hair care, and salon bookings. Built with Jetpack Compose following MVVM architecture.

## 📱 Features

- **Interactive Chat Interface**: Real-time chat with AI assistant for styling tips
- **Quick Suggestions**: Pre-defined suggestion buttons for common queries
- **Smooth Animations**: Fade-in animations for messages and typing indicators
- **Auto-scroll**: Automatically scrolls to latest message
- **Dark Theme**: Modern dark theme optimized for chat experience
- **Responsive UI**: Adapts layout based on chat state (intro vs active chat)

## 🏗️ Architecture

The project follows **MVVM (Model-View-ViewModel)** architecture pattern:

```
app/
├── data/
│   ├── models/
│   │   ├── ChatMessage.kt      # Data model for chat messages
│   │   └── ChatRepository.kt   # Repository for data operations
├── ui/
│   ├── ChatScreen.kt           # Main UI composable
│   ├── componets/
│   │   └── SuggestionChip.kt   # Reusable suggestion button component
│   └── theme/                  # Theme configuration (Colors, Typography)
├── vm/
│   └── ChatViewModel.kt       # ViewModel managing chat state
└── MainActivity.kt             # Entry point
```

### Architecture Layers

1. **UI Layer** (`ui/`): Jetpack Compose screens and components
2. **ViewModel Layer** (`vm/`): Manages UI state using StateFlow
3. **Data Layer** (`data/`): Repository pattern for data access
4. **Models**: Data classes representing domain entities

## 🛠️ Tech Stack

### Core Technologies
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern declarative UI framework
- **Material 3** - Material Design 3 components
- **MVVM Architecture** - Clean separation of concerns
- **Kotlin Coroutines** - Asynchronous programming
- **StateFlow** - Reactive state management

### Libraries Used
```kotlin
// Jetpack Compose
androidx.compose.ui:ui
androidx.compose.material3:material3
androidx.compose.foundation:foundation
androidx.compose.material:material-icons-extended

// Lifecycle & ViewModel
androidx.lifecycle:lifecycle-runtime-ktx:2.8.3
androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3
androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3

// Coroutines
org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0
org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0
```

## 📦 Setup Instructions

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or higher
- Android SDK 24+ (minimum SDK: 24, target SDK: 36)
- Gradle 8.0+

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd AI_Styling_Assistant
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the project directory

3. **Sync Gradle**
   - Android Studio will automatically sync Gradle dependencies
   - If not, click "Sync Now" when prompted

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button (▶️) or press `Shift + F10`
   - Select your target device

### Build Variants
- **Debug**: Default build for development
- **Release**: Optimized build for production (minification disabled for now)

## 🎨 UI Components

### Main Screen (`ChatScreen`)
- **Header Bar**: AI Assistant avatar, title, and menu options
- **Intro Banner**: Welcome message (shown when chat hasn't started)
- **Suggestion Grid**: 2x2 grid of quick action buttons
- **Chat Messages**: Scrollable list of user and AI messages
- **Typing Indicator**: Animated dots showing AI is typing
- **Progress Card**: "Help AI to Complete your Choices" with progress percentage
- **Input Bar**: Text field with microphone icon and send button
- **Disclaimer**: AI-generated content notice

### Key Features
- **Conditional UI**: Intro banner and suggestions hide when chat starts
- **Auto-scroll**: Automatically scrolls to latest message
- **Smooth Animations**: Fade-in animations for new messages
- **Typing Animation**: Animated dots with staggered timing

## 🔄 Data Flow

1. **User Action**: User taps suggestion or types message
2. **ViewModel**: `ChatViewModel.sendUserMessage()` is called
3. **Repository**: `ChatRepository.getAiReplyFor()` fetches response from JSON
4. **State Update**: ViewModel updates `StateFlow<List<ChatMessage>>`
5. **UI Update**: Compose recomposes based on new state
6. **Animation**: New message appears with fade-in animation

## 📊 Data Source

AI responses are stored in `app/src/main/assets/responses.json`:

```json
{
  "quick_ready_tips": [...],
  "monsoon_makeup_tips": [...],
  "need_booking_helps": [...],
  "get_ready_for_birthday": [...],
  "default": [...]
}
```

The repository randomly selects a response from the appropriate category.

## 🎯 Assignment Requirements Checklist

### ✅ UI Implementation
- [x] Chat interface with AI avatar on top
- [x] Chat bubbles (AI and User style)
- [x] Quick suggestion buttons
- [x] Scrollable chat view
- [x] Input field + Send button
- [x] Visual accuracy (colors, fonts, paddings)
- [x] Bonus: Animate chat messages (fade-in animation)

### ✅ Functionality
- [x] Tapping suggestion appends user message and simulates AI reply
- [x] Support vertical scroll
- [x] Smooth chat addition at bottom
- [x] Auto-scroll to latest message

### ✅ Architecture & Code Quality
- [x] MVVM architecture
- [x] Jetpack Compose
- [x] ViewModel + Flow (StateFlow)
- [x] Clean separation of concerns (UI, ViewModel, Repository)

### ✅ Tech Stack
- [x] Kotlin
- [x] Android Jetpack Components
- [x] MVVM
- [x] Coroutines / Flow
- [x] Jetpack Compose

### ⚠️ Optional Bonus Features
- [ ] Dependency Injection (Hilt/Koin) - Not implemented
- [ ] Local Data Store (Room/DataStore) - Not implemented
- [ ] Lottie animation for AI avatar - Not implemented

## 📝 Code Structure

### ViewModel (`ChatViewModel`)
- Manages chat state using `StateFlow`
- Handles user messages and AI responses
- Simulates typing delay for realistic chat experience

### Repository (`ChatRepository`)
- Loads responses from JSON assets
- Provides helper methods for creating messages
- Handles async operations with coroutines

### UI Components
- **ChatScreen**: Main composable orchestrating the entire UI
- **MessageBubble**: Individual chat message component
- **SuggestionChip**: Reusable suggestion button
- **TypingIndicator**: Animated typing dots
- **HeaderBar**: Top navigation bar with avatar

## 🎨 Theme Configuration

The app uses a custom dark theme defined in `ui/theme/`:

- **Background**: `#060606` (Near black)
- **Surface**: `#101012` (Dark gray)
- **Primary**: `#8D7BFF` (Purple)
- **Secondary**: `#FFC857` (Amber/Yellow)
- **Text**: `#F5F5F7` (Light gray)

## 🚀 Future Enhancements

Potential improvements for production:
- Add Dependency Injection (Hilt)
- Implement local data persistence (Room/DataStore)
- Add Lottie animations for AI avatar
- Support voice input
- Add chat history persistence
- Implement real AI integration (OpenAI API)
- Add unit and UI tests
- Support multiple languages

## ⏱️ Development Time

- **Estimated Time**: ~1.5 days
- **Actual Time**: ~1.5 days

## 📄 License

This project is created for educational purposes as part of an assignment.

## 👨‍💻 Author

Developed as part of Android Development assignment.

---

**Note**: All AI responses are generated from local JSON file. The app does not connect to any external AI service.

