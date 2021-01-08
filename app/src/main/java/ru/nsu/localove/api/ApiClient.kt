package ru.nsu.localove.api

import android.util.Log
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.util.*
import javax.inject.Inject
import kotlin.text.Charsets.UTF_8

const val AUTHORIZATION_HEADER = "Authorization"
const val ACCESS_TOKEN_PREFIX = "Bearer "

class ApiClient @KtorExperimentalAPI @Inject constructor(
    val http: HttpClient = httpClient(),
    val baseUrl: String = "http://10.0.2.2:8080",
    val tokenService: TokenService
) {

    val mapper: JsonMapper = JsonMapper.builder().findAndAddModules().build()

    suspend inline fun <reified T> fetch(
        method: HttpMethod,
        path: String,
        body: Any?,
        headers: Map<String, String>,
        withAuth: Boolean = true
    ): HttpResponse<T> {
        Log.d("Http", "trying to send request: $path, $method, $body")
        val response = httpCall(method, path, body, headers, withAuth)
        val responseBody = response.readText(UTF_8)
        return deserializeHttpResponse(response.status.value, responseBody)
            ?: error("received error response: $response, body: $responseBody")
    }

    suspend fun fetchUnit(
        method: HttpMethod,
        path: String,
        body: Any?,
        headers: Map<String, String>,
        withAuth: Boolean = true
    ): UnitHttpResponse {
        val response = httpCall(method, path, body, headers, withAuth)
        val responseBody = response.readText(UTF_8)
        return deserializeEmptyHttpResponse(response.status.value, responseBody)
            ?: error("received error response: $response, body: $responseBody")
    }

    private fun deserializeEmptyHttpResponse(status: Int, body: String): UnitHttpResponse? =
        when (status) {
            200 -> UnitHttpResponse.Success
            400 -> UnitHttpResponse.Error(mapper.readValue(body))
            else -> null
        }

    inline fun <reified T> deserializeHttpResponse(status: Int, body: String): HttpResponse<T>? =
        when (status) {
            200 -> HttpResponse.Success(mapper.readValue(body))
            400 -> HttpResponse.Error(mapper.readValue(body))
            else -> null
        }

    suspend fun httpCall(
        method: HttpMethod, path: String, body: Any?,
        headers: Map<String, String>, withAuth: Boolean = true
    ): io.ktor.client.statement.HttpResponse {
        Log.d("Http", "trying to send request: $path, $method, $body")
        return http.request<HttpStatement>("$baseUrl/${path.trimStart('/')}") {
            contentType(Json)
            body?.let { this.body = body }
            this.method = method
            this.headers {
                headers.forEach(this::append)
                if (withAuth) {
                    this.append(
                        AUTHORIZATION_HEADER,
                        ACCESS_TOKEN_PREFIX + tokenService.accessToken
                    )
                }
            }
        }.execute()
    }
}