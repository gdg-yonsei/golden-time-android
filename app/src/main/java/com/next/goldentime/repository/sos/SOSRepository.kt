package com.next.goldentime.repository.sos

import kotlinx.coroutines.flow.Flow

interface SOSRepository {
    suspend fun postSOS()
    fun getSOS(id: Int): Flow<SOS?>
}

data class SOS(
    val id: Int,
    val location: String,
    val time: String
)