package com.next.goldentime.repository.case

import com.next.goldentime.framework.RetrofitClient
import com.next.goldentime.framework.emitResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class CaseAPIRepository : CaseRepository {
    companion object {
        private val client: CaseAPIClient =
            RetrofitClient.create(CaseAPIClient::class.java)
    }

    override fun listCases() = flow {
        val response = client.listCases()
        emitResponse(response)
    }.map { it.cases }.flowOn(Dispatchers.IO)

    override fun getCase(id: Int) = flow {
        val response = client.getCase(id)
        emitResponse(response)
    }.flowOn(Dispatchers.IO)
}

/**
 * Data source
 */
private interface CaseAPIClient {
    @GET("case")
    suspend fun listCases(): Response<ListCasesResponse>

    @GET("case/{id}")
    suspend fun getCase(
        @Path("id") id: Int
    ): Response<Case>
}

private data class ListCasesResponse(val cases: List<Case>)