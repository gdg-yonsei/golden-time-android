package com.next.goldentime.repository.sos

import android.util.Log
import com.next.goldentime.data.fixtures.sosA
import com.next.goldentime.data.fixtures.sosB
import com.next.goldentime.data.fixtures.sosStateA
import com.next.goldentime.data.fixtures.sosStateB
import com.next.goldentime.repository.user.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class SOSStaticRepository : SOSRepository {
    override fun getSOS(sosId: Int) = flow {
        when (sosId) {
            1 -> emit(sosA)
            2 -> emit(sosB)
        }
    }

    override fun watchSOSState(sosId: Int) = flow {
        while (true) {
            when (sosId) {
                1 -> emit(sosStateA)
                2 -> emit(sosStateB)
            }

            delay(3000)
        }
    }

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