package com.next.goldentime.framework

import android.util.Log
import com.next.goldentime.data.URL_BASE
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val RetrofitClient: Retrofit =
    Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(GsonConverterFactory.create())
        .build()

suspend fun <T> FlowCollector<T>.emitResponse(response: Response<T>) {
    try {
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            Log.e("API Call", response.errorBody().toString())
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
