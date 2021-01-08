package ru.nsu.localove.security.login

sealed class LoginState {
    object Success : LoginState()
    object WrongCredentials: LoginState()
}