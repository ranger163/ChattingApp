package me.inassar.demos.socialapp.data.remote.source

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.inassar.demos.socialapp.common.ENDPOINT_CHAT_CONNECT
import me.inassar.demos.socialapp.common.ENDPOINT_CHAT_HISTORY
import me.inassar.demos.socialapp.common.ENDPOINT_FRIEND_LIST
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.chatRoom.ChatRoomResponseDto
import me.inassar.demos.socialapp.data.remote.dto.chatRoom.MessageResponseDto
import me.inassar.demos.socialapp.data.remote.dto.friendList.response.FriendListResponseDto

class ChatRemoteImpl(private val client: HttpClient) : ChatRemote {

    private var webSocket: WebSocketSession? = null

    override suspend fun getFriendList(token: String?): ResponseResource<FriendListResponseDto> =
        try {
            val response = client.get<FriendListResponseDto>(ENDPOINT_FRIEND_LIST) {
                header(
                    "Authorization",
                    "Bearer $token"
                )
            }
            when (response.data) {
                null -> ResponseResource.error(response)
                else -> ResponseResource.success(response)
            }
        } catch (e: Exception) {
            ResponseResource.error(FriendListResponseDto(error = FriendListResponseDto.Error("Oops, something bad happened :(")))
        }

    override suspend fun getRoomHistory(
        token: String?,
        receiver: String
    ): ResponseResource<ChatRoomResponseDto> =
        try {
            val response = client.get<ChatRoomResponseDto>(ENDPOINT_CHAT_HISTORY) {
                parameter("receiver", receiver)
                header(
                    "Authorization",
                    "Bearer $token"
                )
            }

            when (response.data) {
                null -> ResponseResource.error(response)
                else -> ResponseResource.success(response)
            }
        } catch (e: Exception) {
            ResponseResource.error(ChatRoomResponseDto(error = ChatRoomResponseDto.Error("Oops, something bad happened :(")))
        }

    override suspend fun connectToSocket(
        sender: String,
        receiver: String,
        token: String
    ): ResponseResource<String> = try {
        println("Websocket: CONNECTING")
        webSocket = client.webSocketSession {
            url(ENDPOINT_CHAT_CONNECT).apply {
                parameter("sender", sender)
                parameter("receiver", receiver)
                header(
                    "Authorization",
                    "Bearer $token"
                )
            }
        }
        if (webSocket?.isActive == true) {
            println("Websocket: CONNECTED")
            ResponseResource.success("Connected")
        } else {
            println("Websocket: CONNECTING FAILED")
            ResponseResource.error("Couldn't establish a connection.")
        }
    } catch (e: Exception) {
        e.printStackTrace()
        ResponseResource.error(e.localizedMessage ?: "Unknown error")
    }

    override suspend fun sendMessage(message: String) {
        try {
            webSocket?.send(Frame.Text(message))
            println("WebSocket: MessageSent -> $message")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun receiveMessage(): Flow<MessageResponseDto> = try {
        webSocket?.incoming
            ?.receiveAsFlow()
            ?.filter { it is Frame.Text }
            ?.map {
                val json = (it as? Frame.Text)?.readText().orEmpty()
                println("WebSocket: Message received -> $json")
                val messageResponseDto = Json.decodeFromString<MessageResponseDto>(json)
                messageResponseDto
            } ?: flow { }
    } catch (e: Exception) {
        e.printStackTrace()
        flow { }
    }

    override suspend fun disconnectSocket() {
        webSocket?.close()
        println("WebSocket: CLOSED")
    }
}