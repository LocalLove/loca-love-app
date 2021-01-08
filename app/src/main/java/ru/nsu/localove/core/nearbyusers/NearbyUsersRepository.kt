package ru.nsu.localove.core.nearbyusers

import com.localove.api.user.Gender
import com.localove.api.user.ProfileCard
import ru.nsu.localove.api.ApiClient
import ru.nsu.localove.api.get
import javax.inject.Inject

class NearbyUsersRepository @Inject constructor(
        private val apiClient: ApiClient
) {

    suspend fun fetchUserCard(userId: Long): UserCardState =
            apiClient.get<ProfileCard>(path = "/user/${userId}/card", withAuth = true)
                    .transform(onSuccess = {
                        UserCardState.Valid(it)
                    }, onError = { UserCardState.NotFound })

    suspend fun fetchAvatar(pictureId: Long): PictureState =
            apiClient.get<ByteArray>(path = "/pictures/${pictureId}", withAuth = true)
                    .transform(onSuccess = {
                        // TODO: пока так, потом применить как-нибудь знание о типе изображения
                        PictureState.Valid(it)
                    }, onError = { PictureState.NotFound })

    fun getNearbyUsersIds(): List<Long> {
        // WiFi-Direct logic
        return List(50) {42}
    }
}