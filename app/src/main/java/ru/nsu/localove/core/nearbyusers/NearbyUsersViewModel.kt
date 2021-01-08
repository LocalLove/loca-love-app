package ru.nsu.localove.core.nearbyusers

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.localove.api.user.ProfileCard
import kotlinx.coroutines.launch

class NearbyUsersViewModel @ViewModelInject constructor(
        private val repository: NearbyUsersRepository
) : ViewModel() {

    val nearbyUsers : LiveData<MutableList<ProfileCard>> = MutableLiveData(mutableListOf())

    fun refreshNearbyUsers() {
        nearbyUsers.value?.clear()
        viewModelScope.launch {
            val nearbyUsersIds = repository.getNearbyUsersIds()
            for (userId in nearbyUsersIds) {
                val userCardState = repository.fetchUserCard(userId)
                if (userCardState is UserCardState.Valid) {
                    nearbyUsers.value?.add(userCardState.userCard)
                }
            }
        }
    }

}