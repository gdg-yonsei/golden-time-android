package com.next.goldentime.repository.sos

import com.next.goldentime.framework.RetrofitClient
import com.next.goldentime.framework.emitResponse
import com.next.goldentime.repository.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class SOSAPIRepository : SOSRepository {
    companion object {
        private val client: SOSAPIClient =
            RetrofitClient.create(SOSAPIClient::class.java)
    }

    override fun requestSOS(user: User, location: Location): Flow<RequestSOSResponse> = flow {
        val response = client.requestSOS(user, "${location.latitude},${location.longitude}")
        emitResponse(response)
    }.flowOn(Dispatchers.IO)

    override fun watchRescuers(sosId: Int): Flow<GetRescuersResponse> = flow {
        while (true) {
            val response = client.getRescuers(sosId)
            emitResponse(response)

            delay(3000)
        }
    }.flowOn(Dispatchers.IO)

    override fun getPatient(sosId: Int): Flow<GetPatientResponse> = flow {
        val response = client.getPatient(sosId)
        emitResponse(response)
    }.flowOn(Dispatchers.IO)

    override fun acceptSOS(sosId: Int) = flow {
        val response = client.acceptSOS(sosId)
        emitResponse(response)
    }.flowOn(Dispatchers.IO)

    override fun postLocation(sosId: Int, location: Location) = flow {
        val response = client.postLocation(sosId, "${location.latitude},${location.longitude}")
        emitResponse(response)
    }.flowOn(Dispatchers.IO)

    override fun markAsArrived(sosId: Int) = flow {
        val response = client.markAsArrived(sosId)
        emitResponse(response)
    }.flowOn(Dispatchers.IO)

    override fun completeSOS(sosId: Int) = flow {
        val response = client.completeSOS(sosId)
        emitResponse(response)
    }.flowOn(Dispatchers.IO)
}

private interface SOSAPIClient {
    @POST("sos")
    suspend fun requestSOS(
        @Field("user") user: User,
        @Field("location") location: String
    ): Response<RequestSOSResponse>

    @GET("sos/{id}/rescuers")
    suspend fun getRescuers(
        @Path("id") id: Int
    ): Response<GetRescuersResponse>

    @GET("sos/{id}/patient")
    suspend fun getPatient(
        @Path("id") id: Int
    ): Response<GetPatientResponse>

    @POST("sos/{id}/rescuers/accept")
    suspend fun acceptSOS(
        @Path("id") id: Int
    ): Response<Void>

    @POST("sos/{id}/rescuers/location")
    suspend fun postLocation(
        @Path("id") id: Int,
        @Field("location") location: String
    ): Response<Void>

    @POST("sos/{id}/rescuers/arrived")
    suspend fun markAsArrived(
        @Path("id") id: Int
    ): Response<Void>

    @POST("sos/{id}/rescuers/done")
    suspend fun completeSOS(
        @Path("id") id: Int
    ): Response<Void>
}