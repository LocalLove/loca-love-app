package ru.nsu.localove.security.registration

sealed class RegistrationState {
    object Valid : RegistrationState()
    object InvalidEmail : RegistrationState()
    object InvalidLogin : RegistrationState()
    object UnequalPasswords : RegistrationState()
    object  InvalidPassword : RegistrationState()
}