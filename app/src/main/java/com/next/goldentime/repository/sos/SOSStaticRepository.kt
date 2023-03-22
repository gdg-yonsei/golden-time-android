package com.next.goldentime.repository.sos

import android.util.Log
import com.next.goldentime.data.fixtures.*
import com.next.goldentime.repository.location.Location
import com.next.goldentime.repository.profile.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SOSStaticRepository : SOSRepository {
    override fun getSOSInfo(id: Int) = flow {
        delay(500)

        when (id) {
            1 -> emit(sosInfoA)
            2 -> emit(sosInfoB)
        }
    }.flowOn(Dispatchers.IO)

    override fun watchSOSState(id: Int) = flow {
        emit(sosStateA)
        delay(3000)
        emit(sosStateB)
        delay(3000)
        emit(sosStateC)
        delay(3000)
        emit(sosStateD)
        delay(3000)
        emit(sosStateE)
        delay(3000)
        emit(sosStateF)
    }.flowOn(Dispatchers.IO)

    override suspend fun requestSOS(profile: Profile, location: Location): Int {
        delay(500)

        return when (profile.name) {
            "A" -> 1
            "B" -> 2
            else -> 1
        }
    }

    override suspend fun acceptSOS(id: Int) {
        delay(500)

        Log.d("SOS", "SOS Successfully accepted!")
    }

    override suspend fun postLocation(id: Int, location: Location) {
        delay(500)

        Log.d("SOS", "Location Successfully posted!")
    }

    override suspend fun markAsArrived(id: Int) {
        delay(500)

        Log.d("SOS", "Successfully marked as arrived!")
    }

    override suspend fun completeSOS(id: Int) {
        delay(500)

        Log.d("SOS", "SOS Successfully completed!")
    }
}