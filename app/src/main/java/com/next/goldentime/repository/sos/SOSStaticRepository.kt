package com.next.goldentime.repository.sos

import com.next.goldentime.data.sosFixture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SOSStaticRepository : SOSRepository {
    private val _sos: Flow<SOS> = flow {
        emit(sosFixture)
    }

    override suspend fun postSOS() {

    }

    override fun getSOS(id: Int): Flow<SOS?> {
        return _sos
    }
}