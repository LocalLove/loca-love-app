package ru.nsu.localove.security.login

import com.localove.api.security.Credentials
import com.localove.api.security.TokenDto
import ru.nsu.localove.api.ApiClient
import ru.nsu.localove.api.TokenService
import ru.nsu.localove.api.post
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiClient: ApiClient,

    private val tokenService: TokenService
) {

    suspend fun signIn(login: String, password: String): LoginState =
        apiClient.post<TokenDto>(path = "/sign-in", body = Credentials(login, password), withAuth = false)
            .transform(onSuccess = {
                tokenService.accessToken = it.token
                LoginState.Success
            },
            onError = { LoginState.WrongCredentials })
}