package com.example.jogodavelhamobile.data

import kotlinx.coroutines.flow.Flow

interface RealTimeMessagingClient {

    fun getGameStateStream() : Flow<GameState>
    suspend fun sendAction(action: Maketurn)
    suspend fun close()

}