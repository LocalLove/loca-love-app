package ru.nsu.localove.security.session

import javax.inject.Inject

class UserSession @Inject constructor() {

    private var accessToken: String? = null

    fun open(accessToken: String) {
        this.accessToken = accessToken
    }

    fun accessToken(): String? = accessToken

    fun close() {
        accessToken = null
    }
}