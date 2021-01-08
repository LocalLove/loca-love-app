package ru.nsu.localove.security.registration

import com.localove.api.ErrorType
import com.localove.api.security.UserRegistrationDto
import ru.nsu.localove.api.ApiClient
import ru.nsu.localove.api.postUnit
import javax.inject.Inject

class RegistrationRepository @Inject constructor(
    private val apiClient: ApiClient,
) {
    suspend fun signUp(userInfo: UserInfo) =
        apiClient.postUnit(
            path = "/sign-up",
            body = userInfo.toUserRegistrationDto(),
            withAuth = false
        ).transform(
            onSuccess = { RegistrationState.Success },
            onError = {
                when (it.error) {
                    ErrorType.EMAIL_EXIST -> RegistrationState.EmailAlreadyExist
                    ErrorType.LOGIN_EXIST -> RegistrationState.LoginAlreadyExist
                    else -> RegistrationState.ServerError
                }
            })


    private fun UserInfo.toUserRegistrationDto() = UserRegistrationDto(
        login = login!!,
        email = email!!,
        password = password!!,
        name = name!!,
        birthDate = birthDate!!,
        gender = gender!!
    )
}