package ru.nsu.localove.core.nearbyusers

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NearbyUsersViewModel @ViewModelInject constructor(
        private val repository: NearbyUsersRepository
) : ViewModel() {

    private val _nearbyUsers = MutableLiveData<List<ProfileCardWithPhoto>>().apply {
        value = listOf()
    }
    val nearbyUsers: LiveData<List<ProfileCardWithPhoto>> = _nearbyUsers

    fun refreshNearbyUsers() {
        _nearbyUsers.value = repository.getNearbyUsers()
    }
}