package me.inassar.demos.socialapp.domain.model.friendList

data class FriendList(
    val friendInfo: List<FriendInfo>?=null,
    val errorMessage: String? = null
) {
    data class FriendInfo(
        val token: String?,
        val email: String?,
        val username: String?,
    )
}
