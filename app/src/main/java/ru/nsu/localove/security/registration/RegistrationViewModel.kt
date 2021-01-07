package ru.nsu.localove.security.registration

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel @ViewModelInject constructor(
    private val repository: RegistrationRepository
) : ViewModel() {
    val userInfo = MutableLiveData<UserInfo>().apply {
        value = UserInfo()
    }

    val readyToRegister = MutableLiveData<Boolean>().apply {
        value = false
    }

    val testHiltString = repository.testHiltString

    private var allFieldsFilled = false

    fun onDataChanged(userInfo: UserInfo): RegistrationState {
        allFieldsFilled = true
        return when {
            !validateProperty(userInfo.email, { true }) -> RegistrationState.InvalidEmail
            !validateProperty(userInfo.login, { true }) -> RegistrationState.InvalidLogin
            !validateProperty(userInfo.password, { true }) -> RegistrationState.InvalidPassword
            !validateProperty(userInfo.passwordConfirmation, { true }) -> RegistrationState.UnequalPasswords
            else -> RegistrationState.Valid
        }.also {
            readyToRegister.value = allFieldsFilled
        }
    }

    fun register(userInfo: UserInfo): RegistrationState = TODO()

    private fun <T> validateProperty(
        property: T?,
        validator: (T) -> Boolean
    ): Boolean {
        return property?.let {
            validator(it)
        } ?: let {
            allFieldsFilled = false
            true
        }
    }
}