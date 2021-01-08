package ru.nsu.localove.core.nearbyusers

import com.localove.api.user.Gender
import com.localove.api.user.ProfileCard
import javax.inject.Inject

class NearbyUsersRepository @Inject constructor() {

    fun getNearbyUsers(): List<ProfileCardWithPhoto> {
        val nearbyUsersIds = getNearbyUsersIds()
        val profileCards = nearbyUsersIds.map { fetchUser(it) }
        val avatars = profileCards.map { fetchAvatar(it.avatarId) }
        return profileCards.zip(avatars).map { (profileCard, avatar) ->
            ProfileCardWithPhoto(
                    id = profileCard.id,
                    age = profileCard.age,
                    name = profileCard.name,
                    gender = profileCard.gender,
                    avatar = avatar,
                    status = profileCard.status
            )
        }
    }

    private fun fetchUser(userId: Long): ProfileCard {
        return ProfileCard(
                id = 42,
                age = 18,
                name = "Ivan",
                gender = Gender.FEMALE,
                avatarId = 0,
                status = "Status......."
        )
    }


    private fun fetchAvatar(pictureId: Long): ByteArray {
        return ByteArray(0)
    }

    private fun getNearbyUsersIds(): List<Long> {
        // WiFi-Direct logic
        return List(50) {42}
    }
}

class ProfileCardWithPhoto(
    val id: Long,
    val age: Int,
    val name: String,
    val gender: Gender,
    val avatar: ByteArray,
    val status: String?
)