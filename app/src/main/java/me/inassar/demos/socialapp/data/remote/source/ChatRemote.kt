package me.inassar.demos.socialapp.data.remote.source

import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.friendList.response.FriendListResponseDto

interface ChatRemote {
    suspend fun getFriendList(token: String?): ResponseResource<FriendListResponseDto>
}