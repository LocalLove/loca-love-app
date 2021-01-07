package ru.nsu.localove.security.session

class UserSession {

    private var accessToken: String? = null

    fun open(accessToken: String) {
        this.accessToken = accessToken
    }

    fun accessToken(): String? = accessToken

    fun close() {
        accessToken = null
    }
}