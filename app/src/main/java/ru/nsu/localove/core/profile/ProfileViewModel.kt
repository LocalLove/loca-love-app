package ru.nsu.localove.core.profile

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.localove.api.user.Profile

class ProfileViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    val user: LiveData<Profile> = liveData {
        emit(profileRepository.getProfile())
    }


}