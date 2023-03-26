package com.next.goldentime.repository.sos

import android.util.Log
import com.next.goldentime.framework.RetrofitClient
import com.next.goldentime.framework.emitResponse
import com.next.goldentime.repository.location.Location
import com.next.goldentime.repository.profile.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.*

class SOSAPIRepository : SOSRepository {
    companion object {
        private val client: SOSAPIClient =
            RetrofitClient.create(SOSAPIClient::class.java)
    }

    override fun getSOSInfo(id: Int) = flow {
        val response = client.getSOSInfo(id)
        emitResponse(response)
    }.map {
        val patient = it.patient
        val (latitude, longitude) = it.location.split(",")

        SOSInfo(patient, Location(latitude.toDouble(), longitude.toDouble()))
    }.flowOn(Dispatchers.IO)

    override fun watchSOSState(id: Int) = flow {
        while (true) {
            val response = client.getSOSState(id)
            emitResponse(response)

            delay(3000)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun requestSOS(profile: Profile, location: Location) =
        withContext(Dispatchers.IO) {
            val response = client.requestSOS(
                RequestSOSRequest(profile, "${location.latitude},${location.longitude}")
            )

            if (response.isSuccessful) {
                response.body()!!.sosId
            } else {
                Log.e("API Call", response.errorBody()!!.string())
            }
        }

    override suspend fun acceptSOS(id: Int) {
        withContext(Dispatchers.IO) {
            client.acceptSOS(id)
        }
    }

    override suspend fun postLocation(id: Int, location: Location) {
        withContext(Dispatchers.IO) {
            client.postLocation(
                id,
                PostLocationRequest("${location.latitude},${location.longitude}")
            )
        }
    }

    override suspend fun markAsArrived(id: Int) {
        withContext(Dispatchers.IO) {
            client.markAsArrived(id)
        }
    }

    override suspend fun completeSOS(id: Int) {
        withContext(Dispatchers.IO) {
            client.completeSOS(id)
        }
    }
}

/**
 * Date source
 */
private interface SOSAPIClient {
    @GET("sos/{id}")
    suspend fun getSOSInfo(
        @Path("id") id: Int
    ): Response<GetSOSInfoResponse>

    @GET("sos/{id}/rescuers")
    suspend fun getSOSState(
        @Path("id") id: Int
    ): Response<SOSState>

    @POST("sos")
    suspend fun requestSOS(
        @Body request: RequestSOSRequest
    ): Response<RequestSOSResponse>

    @POST("sos/{id}/rescuer/accept")
    suspend fun acceptSOS(
        @Path("id") id: Int
    ): Response<Void>

    @POST("sos/{id}/rescuer/location")
    suspend fun postLocation(
        @Path("id") id: Int,
        @Body request: PostLocationRequest
    ): Response<Void>

    @POST("sos/{id}/rescuer/arrived")
    suspend fun markAsArrived(
        @Path("id") id: Int
    ): Response<Void>

    @POST("sos/{id}/done")
    suspend fun completeSOS(
        @Path("id") id: Int
    ): Response<Void>
}

private data class GetSOSInfoResponse(val patient: Profile, val location: String)

private data class RequestSOSRequest(val user: Profile, val location: String)
private data class RequestSOSResponse(val sosId: Int)

private data class PostLocationRequest(val location: String)
