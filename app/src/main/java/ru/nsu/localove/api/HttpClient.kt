package ru.nsu.localove.api

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.util.*
import javax.inject.Inject
import javax.inject.Singleton

@KtorExperimentalAPI
fun httpClient() = HttpClient(CIO) {
    install(JsonFeature){
        serializer = JacksonSerializer(JsonMapper.builder().findAndAddModules().build())
    }
    disableResponseValidation()
}

private fun HttpClientConfig<*>.disableResponseValidation() {
    HttpResponseValidator {
        validateResponse { }
    }
}