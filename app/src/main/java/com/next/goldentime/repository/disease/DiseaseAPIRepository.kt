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

class DiseaseAPIRepository : DiseaseRepository {
    companion object {
        private val client: DiseaseAPIClient =
            RetrofitClient.create(DiseaseAPIClient::class.java)
    }

    override fun listDiseases() = flow {
        val response = client.listDiseases()
        emitResponse(response)
    }.map { it.diseases }.flowOn(Dispatchers.IO)

    override fun getDisease(id: Int) = flow {
        val response = client.getDisease(id)
        emitResponse(response)
    }.flowOn(Dispatchers.IO)
}

/**
 * Data source
 */
private interface DiseaseAPIClient {
    @GET("disease")
    suspend fun listDiseases(): Response<ListDiseasesResponse>

    @GET("disease/{id}")
    suspend fun getDisease(
        @Path("id") id: Int
    ): Response<Disease>
}

private data class ListDiseasesResponse(val diseases: List<Disease>)