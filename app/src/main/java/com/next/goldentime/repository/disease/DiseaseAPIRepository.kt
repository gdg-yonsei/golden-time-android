package com.next.goldentime.repository.disease

import com.next.goldentime.framework.RetrofitClient
import com.next.goldentime.framework.emitResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class DiseaseAPIRepository : DiseaseRepository {
    companion object {
        private val client: DiseaseAPIClient =
            RetrofitClient.create(DiseaseAPIClient::class.java)
    }

    override fun getDiseases(ids: List<Int>?) = flow {
        val response = client.getDiseases(ids?.joinToString(","))
        emitResponse(response)
    }.map { it.diseases }.flowOn(Dispatchers.IO)

    override fun getDisease(id: Int) = flow {
        val response = client.getDisease(id)
        emitResponse(response)
    }.flowOn(Dispatchers.IO)

    override fun getManual(diseaseId: Int) = flow {
        val response = client.getManual(diseaseId)
        emitResponse(response)
    }.map { it.manual }.flowOn(Dispatchers.IO)
}

/**
 * Data source
 */
private interface DiseaseAPIClient {
    @GET("disease")
    suspend fun getDiseases(
        @Query("ids") ids: String?
    ): Response<GetDiseasesResponse>

    @GET("disease/{id}")
    suspend fun getDisease(
        @Path("id") id: Int
    ): Response<Disease>

    @GET("disease/{id}/manual")
    suspend fun getManual(
        @Path("id") diseaseId: Int
    ): Response<GetManualResponse>
}

private data class GetDiseasesResponse(val diseases: List<Disease>)
private data class GetManualResponse(val manual: Manual)