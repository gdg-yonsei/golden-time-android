package com.next.goldentime.repository.profile

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.next.goldentime.util.toIntList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProfileStoreRepository(private val profileStore: DataStore<Preferences>) : ProfileRepository {
    override fun watchProfile() = profileStore.data.map { profile ->
        val name = profile[ProfileStore.Name.key] ?: ""
        val birthDate = profile[ProfileStore.BirthDate.key] ?: ""
        val height = profile[ProfileStore.Height.key] ?: 0
        val weight = profile[ProfileStore.Weight.key] ?: 0
        val bloodType = profile[ProfileStore.BloodType.key] ?: ""
        val allergies = profile[ProfileStore.Allergies.key] ?: ""
        val medications = profile[ProfileStore.Medications.key] ?: ""
        val medicalNotes = profile[ProfileStore.MedicalNotes.key] ?: ""
        val diseases = (profile[ProfileStore.Diseases.key] ?: "").toIntList()

        Profile(
            name,
            birthDate,
            height,
            weight,
            bloodType,
            allergies,
            medications,
            medicalNotes,
            diseases
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun setProfile(
        name: String?,
        birthDate: String?,
        height: Int?,
        weight: Int?,
        bloodType: String?,
        allergies: String?,
        medications: String?,
        medicalNotes: String?,
        diseases: List<Int>?
    ) {
        withContext(Dispatchers.IO) {
            profileStore.edit {
                if (name !== null) it[ProfileStore.Name.key] = name
                if (birthDate !== null) it[ProfileStore.BirthDate.key] = birthDate
                if (height !== null) it[ProfileStore.Height.key] = height
                if (weight !== null) it[ProfileStore.Weight.key] = weight
                if (bloodType !== null) it[ProfileStore.BloodType.key] = bloodType
                if (allergies !== null) it[ProfileStore.Allergies.key] = allergies
                if (medications !== null) it[ProfileStore.Medications.key] = medications
                if (medicalNotes !== null) it[ProfileStore.MedicalNotes.key] = medicalNotes
                if (diseases !== null) it[ProfileStore.Diseases.key] = diseases.joinToString(",")
            }
        }
    }
}

/**
 * Data source
 */
val Context.profileStore: DataStore<Preferences> by preferencesDataStore("profile")

sealed class ProfileStore<T>(val key: Preferences.Key<T>) {
    object Name : ProfileStore<String>(stringPreferencesKey("name"))
    object BirthDate : ProfileStore<String>(stringPreferencesKey("birth_date"))
    object Height : ProfileStore<Int>(intPreferencesKey("height"))
    object Weight : ProfileStore<Int>(intPreferencesKey("weight"))
    object BloodType : ProfileStore<String>(stringPreferencesKey("blood_type"))
    object Allergies : ProfileStore<String>(stringPreferencesKey("allergies"))
    object Medications : ProfileStore<String>(stringPreferencesKey("medications"))
    object MedicalNotes : ProfileStore<String>(stringPreferencesKey("medical_notes"))
    object Diseases : ProfileStore<String>(stringPreferencesKey("diseases"))
}