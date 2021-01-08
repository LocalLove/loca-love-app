package ru.nsu.localove.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.ktor.client.*
import io.ktor.util.*
import ru.nsu.localove.api.httpClient

@Module
@InstallIn(value = [ActivityComponent::class])
class ApiClientModule {

    @KtorExperimentalAPI
    @Provides
    fun provideHttpClient(): HttpClient {
        return httpClient()
    }

    @Provides
    fun provideBaseUrl(): String {
        return "http://10.0.2.2:8080"
    }
}