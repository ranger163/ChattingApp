package me.inassar.demos.socialapp.domain.model.chatRoom

data class RoomHistoryList(
    val roomData: List<Message>? = null,
    val errorMessage: String? = null
) {
    data class Message(
        val sessionId: String? = null,
        val receiver: String?,
        val sender: String?,
        val textMessage: String?,
        val timestamp: Long?,
        val formattedTime: String?,
        val formattedDate: String?,
    )
}