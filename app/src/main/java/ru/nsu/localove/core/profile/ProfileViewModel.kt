package ru.nsu.localove.core.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>().apply {
        value = "ShSerg0699"
    }
    val userName: LiveData<String> = _userName
}