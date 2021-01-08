package ru.nsu.localove.security.registration

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nsu.localove.api.ApiClient

class RegistrationViewModel @ViewModelInject constructor(
    private val repository: RegistrationRepository,
    private val apiClient: ApiClient
) : ViewModel() {
    val userInfo: UserInfo = UserInfo()

    val readyToRegister = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun onDataChanged(userInfoMask: UserInfo): RegistrationState {
        var readyToRegisterLocal = false
        return when {
            !validateEmail(userInfoMask.email) -> RegistrationState.InvalidEmail
            !validateLogin(userInfoMask.login) -> RegistrationState.InvalidLogin
            !validatePassword(userInfoMask.password) -> RegistrationState.InvalidPassword
            !validateName(userInfoMask.name) -> RegistrationState.InvalidPassword
            userInfoMask.password != userInfoMask.passwordConfirmation -> RegistrationState.UnequalPasswords
            else -> {
                readyToRegisterLocal = userInfo
                    .merge(userInfoMask)
                    .isReadyToRegister()
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

    private fun UserInfo.merge(other: UserInfo): UserInfo {
        return this.apply {
            other.email?.let { email = it }
            other.login?.let { email = it }
            other.password?.let { email = it }
            other.name?.let { email = it }
            other.gender?.let { gender = it }
            other.birthDate?.let { birthDate = it }
        }
    }

}