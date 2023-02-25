package com.next.goldentime.ui.screens.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.next.goldentime.repository.profile.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(
    profileRepository: ProfileRepository = ProfileRepository()
) : ViewModel() {
    private val profile = profileRepository.watchProfile()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    init {
        viewModelScope.launch {
            profile.collectLatest {
                _name.value = it.name
            }
        }
    }
}
