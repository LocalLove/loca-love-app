package ru.nsu.localove.api

import android.content.Context
import ru.nsu.localove.LocaLoveApplication
import javax.inject.Inject

class TokenService @Inject constructor() {
    companion object {
        private const val ACCESS_TOKEN_KEY = "loca_love_access_token"
        private const val PREFERENCES_FILE_NAME = "loca_love_access_token"
    }

    private var sharedPreferences = LocaLoveApplication.context?.getSharedPreferences(
        PREFERENCES_FILE_NAME,
        Context.MODE_PRIVATE
    ) ?: error("Context not initialized")

    var accessToken: String? = sharedPreferences.getString(
        ACCESS_TOKEN_KEY,
        null
    )
        set(token) {
            sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, token).apply()
            field = token
        }

    fun isAuthorized() = accessToken != null

}