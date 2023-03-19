package com.next.goldentime.repository.sos

import android.util.Log
import com.next.goldentime.data.fixtures.*
import com.next.goldentime.repository.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SOSStaticRepository : SOSRepository {
    override fun getSOS(sosId: Int) = flow {
        delay(1000)

        when (sosId) {
            1 -> emit(sosA)
            2 -> emit(sosB)
        }
    }.flowOn(Dispatchers.IO)

    override fun watchSOSState(sosId: Int) = flow {
        emit(sosStateA)
        delay(3000)
        emit(sosStateB)
        delay(5000)
        emit(sosStateC)
        delay(5000)
        emit(sosStateD)
        delay(10000)
        emit(sosStateE)
        delay(10000)
        emit(sosStateF)
    }.flowOn(Dispatchers.IO)

    override suspend fun requestSOS(user: User, location: Location): Int {
        return when (user.name) {
            "A" -> 1
            "B" -> 2
            else -> 0
        }
    }

    override suspend fun acceptSOS(sosId: Int) {
        Log.d("SOS", "SOS Successfully accepted!")
    }

    override suspend fun postLocation(sosId: Int, location: Location) {
        Log.d("SOS", "Location Successfully posted!")
    }

    override suspend fun markAsArrived(sosId: Int) {
        Log.d("SOS", "Successfully marked as arrived!")
    }

    override suspend fun completeSOS(sosId: Int) {
        Log.d("SOS", "SOS Successfully completed!")
    }
}