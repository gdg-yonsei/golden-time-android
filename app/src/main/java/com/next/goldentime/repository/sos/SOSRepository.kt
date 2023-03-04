package com.next.goldentime.repository.sos

import com.next.goldentime.repository.user.User
import kotlinx.coroutines.flow.Flow

data class Location(val latitude: Double, val longitude: Double)

/**
 * Repository
 */
interface SOSRepository {
    // By patient
    fun requestSOS(user: User, location: Location): Flow<RequestSOSResponse>
    fun watchRescuers(sosId: Int): Flow<GetRescuersResponse>

    // By rescuers
    fun getPatient(sosId: Int): Flow<GetPatientResponse>
    fun acceptSOS(sosId: Int): Flow<Void>
    fun postLocation(sosId: Int, location: Location): Flow<Void>
    fun markAsArrived(sosId: Int): Flow<Void>
    fun completeSOS(sosId: Int): Flow<Void>
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
