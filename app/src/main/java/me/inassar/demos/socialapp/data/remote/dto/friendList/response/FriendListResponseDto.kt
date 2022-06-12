package me.inassar.demos.socialapp.data.remote.dto.friendList.response


import kotlinx.serialization.Serializable

@Serializable
data class FriendListResponseDto(
    val data: List<FriendListData?>? = null,
    val error: Error? = null
) {
    @Serializable
    data class FriendListData(
        val friendInfo: FriendInfo?,
        val token: String?
    ) {
        @Serializable
        data class FriendInfo(
            val avatar: String?,
            val email: String?,
            val lastMessage: LastMessage?,
            val username: String?
        ) {
            @Serializable
            data class LastMessage(
                val receiver: String?,
                val sender: String?,
                val textMessage: String?,
                val timestamp: Long?
            )
        }
    }

    @Serializable
    data class Error(
        val message: String? = null
    )
}