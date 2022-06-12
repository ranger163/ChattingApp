package me.inassar.demos.socialapp.data.remote.dto.chatRoom


import kotlinx.serialization.Serializable

@Serializable
data class ChatRoomResponseDto(
    val data: List<ChatRoomData?>? = null,
    val error: Error? = null
) {
    @Serializable
    data class ChatRoomData(
        val receiver: String?,
        val sender: String?,
        val textMessage: String?,
        val timestamp: Long?
    )

    @Serializable
    data class Error(
        val message: String? = null
    )
}