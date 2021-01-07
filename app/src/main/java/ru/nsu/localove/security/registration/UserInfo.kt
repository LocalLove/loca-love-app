package ru.nsu.localove.security.registration

import com.localove.api.user.Gender
import java.time.LocalDate

class UserInfo(
    var login: String? = null,

    var password: String? = null,

    var passwordConfirmation: String? = null,

    var email: String? = null,

    var name: String? = null,

    var birthDate: LocalDate? = null,

    var gender: Gender? = null
)