package ru.nsu.localove.security.registration


fun validateEmail(email: String?) = trueIfNull(email, Regex("(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))

fun validateLogin(login: String?) = trueIfNull(login, Regex("[A-Za-z\\d_.]{3,100}\$"))

fun validatePassword(password: String?) = trueIfNull(password, Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@_.]{8,100}\$"))

fun validateName(name: String?) = trueIfNull(name, Regex("[A-Za-z]{1,100}\$"))

private fun trueIfNull(value: String?, regex: Regex): Boolean =
    value?.let {
        regex.matches(it)
    } ?: true

