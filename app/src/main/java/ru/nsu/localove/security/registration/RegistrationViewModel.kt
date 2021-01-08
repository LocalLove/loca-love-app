package ru.nsu.localove.security.registration

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegistrationViewModel @ViewModelInject constructor(
    private val repository: RegistrationRepository,
) : ViewModel() {
    val userInfo: UserInfo = UserInfo()

    val registrationState = MutableLiveData<RegistrationState?>()

    val readyToRegister = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun onDataChanged(userInfoMask: UserInfo): RegistrationFormState {
        var readyToRegisterLocal = false
        return when {
            !validateEmail(userInfoMask.email) -> RegistrationFormState.InvalidEmail
            !validateLogin(userInfoMask.login) -> RegistrationFormState.InvalidLogin
            !validatePassword(userInfoMask.password) -> RegistrationFormState.InvalidPassword
            !validateName(userInfoMask.name) -> RegistrationFormState.InvalidName
            else -> {
                readyToRegisterLocal = userInfo
                    .merge(userInfoMask)
                    .isReadyToRegister()
                RegistrationFormState.Valid
            }
        }.also {
            readyToRegister.value = readyToRegisterLocal
        }
    }

    fun register() {
        viewModelScope.launch {
            registrationState.value = repository.signUp(userInfo)
        }
    }

    private fun UserInfo.isReadyToRegister(): Boolean {
        return email != null && login != null && password != null
                && name != null && gender != null && birthDate != null
    }

    private fun UserInfo.merge(other: UserInfo): UserInfo {
        return this.apply {
            other.email?.let { email = it }
            other.login?.let { login = it }
            other.password?.let { password = it }
            other.name?.let { name = it }
            other.gender?.let { gender = it }
            other.birthDate?.let { birthDate = it }
        }
    }

}