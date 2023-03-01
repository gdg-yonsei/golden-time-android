package com.next.goldentime.repository.disease

import com.next.goldentime.framework.RetrofitClient
import com.next.goldentime.framework.emitResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class DiseaseAPIRepository : DiseaseRepository {
    companion object {
        private val client: DiseaseAPIClient =
            RetrofitClient.create(DiseaseAPIClient::class.java)
    }

    override fun listDiseases(ids: Array<Int>?): Flow<ListDiseasesResponse> = flow {
        val response = client.listDiseases(ids?.joinToString(","))
        emitResponse(response)
    }.flowOn(Dispatchers.IO)

    override fun getDisease(id: Int): Flow<GetDiseaseResponse> = flow {
        val response = client.getDisease(id)
        emitResponse(response)
    }.flowOn(Dispatchers.IO)

    override fun getManual(diseaseId: Int): Flow<GetManualResponse> = flow {
        val response = client.getManual(diseaseId)
        emitResponse(response)
    }.flowOn(Dispatchers.IO)
}

private interface DiseaseAPIClient {
    @GET("disease")
    suspend fun listDiseases(
        @Query("ids") ids: String?
    ): Response<ListDiseasesResponse>

    @GET("disease/{id}")
    suspend fun getDisease(
        @Path("id") id: Int
    ): Response<GetDiseaseResponse>

    @GET("disease/{diseaseId}/manual")
    suspend fun getManual(
        @Path("diseaseId") diseaseId: Int
    ): Response<GetManualResponse>
}
