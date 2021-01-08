package ru.nsu.localove.security.registration

sealed class RegistrationFormState {
    object Valid : RegistrationFormState()
    object InvalidEmail : RegistrationFormState()
    object InvalidLogin : RegistrationFormState()
    object  InvalidPassword : RegistrationFormState()
    object  InvalidName : RegistrationFormState()
    object UnequalPasswords : RegistrationFormState()
}

sealed class RegistrationState {
    object Success : RegistrationState()
    object EmailAlreadyExist : RegistrationState()
    object LoginAlreadyExist : RegistrationState()
    object ServerError: RegistrationState()
}