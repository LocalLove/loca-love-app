package ru.nsu.localove.core.nearbyusers

import com.localove.api.user.ProfileCard

sealed class UserCardState {
    class Valid(val userCard: ProfileCard) : UserCardState()
    object NotFound : UserCardState()
}