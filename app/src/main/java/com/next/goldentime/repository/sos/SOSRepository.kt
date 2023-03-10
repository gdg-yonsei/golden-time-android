package com.next.goldentime.repository.sos

import com.next.goldentime.repository.user.User
import kotlinx.coroutines.flow.Flow

data class SOS(val patient: User, val location: Location)

data class SOSState(val rescuerNum: Int, val closestRescuerDistance: Double, val done: Boolean)

data class Location(val latitude: Double, val longitude: Double)

/**
 * Repository
 */
interface SOSRepository {
    fun getSOS(sosId: Int): Flow<SOS>
    fun watchSOSState(sosId: Int): Flow<SOSState>

    suspend fun requestSOS(user: User, location: Location): Int
    suspend fun acceptSOS(sosId: Int)
    suspend fun postLocation(sosId: Int, location: Location)
    suspend fun markAsArrived(sosId: Int)
    suspend fun completeSOS(sosId: Int)
}
