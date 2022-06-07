package me.inassar.demos.socialapp.data.remote.dto.friendList.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FriendListResponseDto(
    @SerialName("data")
    val `data`: List<FriendListData?>? = null,
    val error: Error? = null
) {
    @Serializable
    data class FriendListData(
        val friendInfo: FriendInfo?,
        val token: String?
    ) {
        @Serializable
        data class FriendInfo(
            val email: String?,
            val username: String?,
            val avatar: String?
        )
    }

    @Serializable
    data class Error(
        val message: String? = null
    )
}