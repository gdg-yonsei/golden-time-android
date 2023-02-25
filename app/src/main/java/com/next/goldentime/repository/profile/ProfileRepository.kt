package com.next.goldentime.repository.profile

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepository {
    private val _profile: Flow<Profile> = flow {
        while (true) {
            emit(Profile())
            delay(3000)
        }
    }

    fun watchProfile(): Flow<Profile> {
        return _profile
    }
}