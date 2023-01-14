package com.example.jogodavelhamobile.data

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KtorRealtimeMessagingClient(private val client: HttpClient) : RealTimeMessagingClient {

    private var session: WebSocketSession? = null

//Line 18 to 31 -> Listen to websocket messages to realtime messages and it contains all the mapping code to map these two actual gamestates object client side
// Ktor made that being possible with not a lot of code

    override fun getGameStateStream(): Flow<GameState> {
        return flow {
            session = client.webSocketSession {
                url("ws://10.0.0.100/play")
            }
            val gameStates = session!!
                .incoming
                .consumeAsFlow()
                .filterIsInstance<Frame.Text>()
                .mapNotNull {
                    Json.decodeFromString<GameState>(it.readText())
                }

            emitAll(gameStates)
        }
    }

    override suspend fun sendAction(action: Maketurn) {

        session?.outgoing?.send(
            Frame.Text("make_turn#${Json.encodeToString(action)}")
        )

    }

    override suspend fun close() {

        session?.close()
        session = null

    }
}