package com.next.goldentime.repository.user

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

class UserStoreRepository(private val userStore: DataStore<Preferences>) : UserRepository {
    private val _user: Flow<User> =
        userStore.data.map {
            User(
                it[UserStore.Name.key] ?: "",
                it[UserStore.BirthDate.key] ?: "",
                it[UserStore.Height.key] ?: 0.0,
                it[UserStore.Weight.key] ?: 0.0,
                it[UserStore.BloodType.key] ?: "",
                it[UserStore.Allergies.key] ?: "",
                it[UserStore.Medications.key] ?: "",
                it[UserStore.MedicalNotes.key] ?: ""
            )
        }.flowOn(Dispatchers.IO)

    private val _name: Flow<String> =
        userStore.data.map { it[UserStore.Name.key] ?: "" }.flowOn(Dispatchers.IO)

    override fun watchUser() = _user
    override fun watchName() = _name

    override suspend fun setUser(user: User) {
        userStore.edit {
            it[UserStore.Name.key] = user.name
            it[UserStore.BirthDate.key] = user.birthDate
            it[UserStore.Height.key] = user.height
            it[UserStore.Weight.key] = user.weight
            it[UserStore.BloodType.key] = user.bloodType
            it[UserStore.Allergies.key] = user.allergies
            it[UserStore.Medications.key] = user.medications
            it[UserStore.MedicalNotes.key] = user.medicalNotes
        }
    }
}

val Context.userStore: DataStore<Preferences> by preferencesDataStore("user")

sealed class UserStore<T>(val key: Preferences.Key<T>) {
    object Name : UserStore<String>(stringPreferencesKey("name"))
    object BirthDate : UserStore<String>(stringPreferencesKey("birth_date"))
    object Height : UserStore<Double>(doublePreferencesKey("height"))
    object Weight : UserStore<Double>(doublePreferencesKey("weight"))
    object BloodType : UserStore<String>(stringPreferencesKey("blood_type"))
    object Allergies : UserStore<String>(stringPreferencesKey("allergies"))
    object Medications : UserStore<String>(stringPreferencesKey("medications"))
    object MedicalNotes : UserStore<String>(stringPreferencesKey("medical_notes"))
}