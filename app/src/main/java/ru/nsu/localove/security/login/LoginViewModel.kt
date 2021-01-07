package ru.nsu.localove.security.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    lateinit var login: LiveData<String>

    lateinit var password: LiveData<String>

    fun signIn() {

    }
}