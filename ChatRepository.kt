package com.example.ai_chatbot.repository

import com.example.ai_chatbot.API.NetworkClient
import com.example.ai_chatbot.model.Content
import com.example.ai_chatbot.model.GeminiRequest
import com.example.ai_chatbot.model.Part


class ChatRepository {
    private val geminiApi = NetworkClient.geminiApi

    suspend fun sendMessage(message: String): Result<String> {
        return try {
            val request = GeminiRequest(
                listOf(
                    Content(
                        listOf(
                            Part(text = message)
                        )
                    )
                )
            )

            val response = geminiApi.generateContent(
                //Put your API key here
                apiKey = "",
                request = request
            )

            if (response.isSuccessful) {
                val geminiResponse = response.body()
                val reply = geminiResponse?.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                Result.success(reply ?: "No response from Gemini")
            } else {
                Result.failure(Exception("API Error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
