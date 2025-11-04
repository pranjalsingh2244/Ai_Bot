package com.humblecoders.humblebot.model

import com.example.ai_chatbot.model.Content

data class GeminiResponse(
    val candidates: List<Candidate>
)

data class Candidate(
    val content: Content
)
