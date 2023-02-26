package com.next.goldentime.repository.profile

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ProfileStoreRepository(private val profileStore: DataStore<Preferences>) : ProfileRepository {
    private val _profile: Flow<Profile> =
        profileStore.data.map {
            Profile(
                it[ProfileStore.Name.key] ?: "",
                it[ProfileStore.BirthDate.key] ?: "",
                it[ProfileStore.Height.key] ?: 0.0,
                it[ProfileStore.Weight.key] ?: 0.0,
                it[ProfileStore.MedicalNotes.key] ?: ""
            )
        }.flowOn(Dispatchers.IO)

    private val _name: Flow<String> =
        profileStore.data.map { it[ProfileStore.Name.key] ?: "" }.flowOn(Dispatchers.IO)
    private val _birthDate: Flow<String> =
        profileStore.data.map { it[ProfileStore.BirthDate.key] ?: "" }.flowOn(Dispatchers.IO)
    private val _height: Flow<Double> =
        profileStore.data.map { it[ProfileStore.Height.key] ?: 0.0 }.flowOn(Dispatchers.IO)
    private val _weight: Flow<Double> =
        profileStore.data.map { it[ProfileStore.Weight.key] ?: 0.0 }.flowOn(Dispatchers.IO)
    private val _medicalNotes: Flow<String> =
        profileStore.data.map { it[ProfileStore.MedicalNotes.key] ?: "" }.flowOn(Dispatchers.IO)

    override fun watchProfile() = _profile
    override suspend fun setProfile(profile: Profile) {
        profileStore.edit {
            it[ProfileStore.Name.key] = profile.name
            it[ProfileStore.BirthDate.key] = profile.birthDate
            it[ProfileStore.Height.key] = profile.height
            it[ProfileStore.Weight.key] = profile.weight
            it[ProfileStore.MedicalNotes.key] = profile.medicalNotes
        }
    }

    override fun watchName() = _name
    override fun watchBirthDate() = _birthDate
    override fun watchHeight() = _height
    override fun watchWeight() = _weight
    override fun watchMedicalNotes() = _medicalNotes
}

val Context.profileStore: DataStore<Preferences> by preferencesDataStore("profile")

sealed class ProfileStore<T>(val key: Preferences.Key<T>) {
    object Name : ProfileStore<String>(stringPreferencesKey("name"))
    object BirthDate : ProfileStore<String>(stringPreferencesKey("birth_date"))
    object Height : ProfileStore<Double>(doublePreferencesKey("height"))
    object Weight : ProfileStore<Double>(doublePreferencesKey("weight"))
    object MedicalNotes : ProfileStore<String>(stringPreferencesKey("medical_notes"))
}