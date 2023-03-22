package com.next.goldentime.repository.sos

import com.next.goldentime.repository.location.Location
import com.next.goldentime.repository.profile.Profile
import kotlinx.coroutines.flow.Flow

data class SOSInfo(val patient: Profile, val location: Location)

data class SOSState(val rescuerNum: Int, val closestRescuerDistance: Double, val done: Boolean)

/**
 * Repository
 */
interface SOSRepository {
    fun getSOSInfo(id: Int): Flow<SOSInfo>
    fun watchSOSState(id: Int): Flow<SOSState>

    suspend fun requestSOS(profile: Profile, location: Location): Int
    suspend fun acceptSOS(id: Int)
    suspend fun postLocation(id: Int, location: Location)
    suspend fun markAsArrived(id: Int)
    suspend fun completeSOS(id: Int)
}
