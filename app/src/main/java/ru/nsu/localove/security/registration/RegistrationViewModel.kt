package ru.nsu.localove.security.registration

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nsu.localove.api.ApiClient

class RegistrationViewModel @ViewModelInject constructor(
    private val repository: RegistrationRepository,
    private val apiClient: ApiClient
) : ViewModel() {
    val readyToRegister = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun onDataChanged(userInfo: UserInfo): RegistrationState {
        var readyToRegisterLocal = false
        return when {
            !validateEmail(userInfo.email) -> RegistrationState.InvalidEmail
            !validateLogin(userInfo.login) -> RegistrationState.InvalidLogin
            !validatePassword(userInfo.password) -> RegistrationState.InvalidPassword
            !validateName(userInfo.name) -> RegistrationState.InvalidPassword
            userInfo.password != userInfo.passwordConfirmation -> RegistrationState.UnequalPasswords
            else -> {
                readyToRegisterLocal = true
                RegistrationState.Valid
            }
        }.also {
            readyToRegister.value = readyToRegisterLocal
        }
    }

    fun register(userInfo: UserInfo): RegistrationState = TODO()

    private fun UserInfo.isReadyToRegister(): Boolean {
        return email != null && login != null && password != null
                && name != null && gender != null && birthDate != null
    }
}