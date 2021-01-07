package ru.nsu.localove.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import io.ktor.client.*
import io.ktor.util.*
import ru.nsu.localove.api.httpClient
import ru.nsu.localove.security.MainActivity
import javax.inject.Singleton

@Module
@InstallIn(value = [MainActivity::class])
object ApiClientModule {

    @KtorExperimentalAPI
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return httpClient()
    }
    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return "localhost"
    }
}