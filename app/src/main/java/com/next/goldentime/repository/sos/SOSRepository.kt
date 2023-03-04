package com.next.goldentime.repository.sos

import com.next.goldentime.repository.user.User
import kotlinx.coroutines.flow.Flow

data class Location(val latitude: Double, val longitude: Double)

/**
 * Repository
 */
interface SOSRepository {
    // By patient
    suspend fun requestSOS(user: User, location: Location): RequestSOSResponse
    fun watchRescuers(sosId: Int): Flow<GetRescuersResponse>

    // By rescuers
    fun getPatient(sosId: Int): Flow<GetPatientResponse>
    suspend fun acceptSOS(sosId: Int)
    suspend fun postLocation(sosId: Int, location: Location)
    suspend fun markAsArrived(sosId: Int)
    suspend fun completeSOS(sosId: Int)
}

interface RequestSOSResponse {
    val sosId: Int
}

interface GetRescuersResponse {
    val rescuerNum: Int
    val arrivedRescuerNum: Int
    val closestRescuerLocation: String
    val done: Boolean
}

interface GetPatientResponse {
    val user: User
    val location: String
}
