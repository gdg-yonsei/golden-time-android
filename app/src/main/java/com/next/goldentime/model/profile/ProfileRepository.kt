package com.next.goldentime.model.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProfileRepository {
    private val _profile = MutableLiveData<Profile>()

    init {
        _profile.value = Profile()
    }

    fun watchProfile(): LiveData<Profile> {
        return _profile
    }

    fun setProfile(profile: Profile) {
        _profile.value = profile
    }
}