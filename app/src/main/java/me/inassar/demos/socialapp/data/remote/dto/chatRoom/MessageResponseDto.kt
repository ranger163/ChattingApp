package me.inassar.demos.socialapp.data.remote.dto.chatRoom


import kotlinx.serialization.Serializable

@Serializable
data class MessageResponseDto(
    val sessionId:String?,
    val receiver: String?,
    val sender: String?,
    val textMessage: String?,
    val timestamp: Long?
)