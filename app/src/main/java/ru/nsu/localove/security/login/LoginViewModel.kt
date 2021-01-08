package ru.nsu.localove.security.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    val loginInfo: LoginInfo = LoginInfo()

    val loginState: MutableLiveData<LoginState?> = MutableLiveData()

    fun signIn() {
        val login = loginInfo.login
        val password = loginInfo.password

        if (login == null || password == null) {
            loginState.value = LoginState.WrongCredentials
            return
        }

        viewModelScope.launch {
            loginState.value = loginRepository.signIn(login, password)
        }
    }
}