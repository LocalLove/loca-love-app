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

    fun onDataChanged(userInfo: UserInfo): RegistrationState {
        var allFieldsFilled = true
        val onNullCallback = {
            allFieldsFilled = false
        }

        return when {
            !validateProperty(
                userInfo.email,
                { true },
                onNullCallback
            ) -> RegistrationState.InvalidEmail
            !validateProperty(
                userInfo.login,
                { true },
                onNullCallback
            ) -> RegistrationState.InvalidLogin
            !validateProperty(
                userInfo.password,
                { true },
                onNullCallback
            ) -> RegistrationState.InvalidPassword
            !validateProperty(
                userInfo.passwordConfirmation,
                { true },
                onNullCallback
            ) -> RegistrationState.UnequalPasswords
            else -> RegistrationState.Valid
        }.also {
            readyToRegister.value = allFieldsFilled
        }
    }

    fun register(userInfo: UserInfo): RegistrationState = TODO()

    private fun <T> validateProperty(
        property: T?,
        validator: (T) -> Boolean,
        onNullCallback: () -> Unit
    ): Boolean {
        return property?.let {
            validator(it)
        } ?: let {
            onNullCallback()
            true
        }
    }
}