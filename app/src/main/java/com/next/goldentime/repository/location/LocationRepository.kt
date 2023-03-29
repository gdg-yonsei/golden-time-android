package com.next.goldentime.repository.location

import kotlinx.coroutines.flow.Flow

data class Location(val latitude: Double, val longitude: Double)

/**
 * Repository
 */
interface LocationRepository {
    fun getLocation(): Flow<Location?>
}