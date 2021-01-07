package ru.nsu.localove.core.nearbyusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NearbyUsersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Nearby users Fragment"
    }
    val text: LiveData<String> = _text
}