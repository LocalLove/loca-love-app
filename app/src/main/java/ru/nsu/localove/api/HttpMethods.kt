package ru.nsu.localove.api

import io.ktor.http.HttpMethod.Companion.Delete
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpMethod.Companion.Put

suspend inline fun <reified T> ApiClient.post(
        path: String = "",
        body: Any? = null,
        headers: Map<String, String> = mapOf(),
        withAuth: Boolean = true
): HttpResponse<T> = fetch(Post, path, body, headers, withAuth)

suspend inline fun <reified T> ApiClient.get(
        path: String = "",
        body: Any? = null,
        headers: Map<String, String> = mapOf(),
        withAuth: Boolean = true
): HttpResponse<T> = fetch(Get, path, body, headers, withAuth)

suspend inline fun <reified T> ApiClient.put(
        path: String = "",
        body: Any? = null,
        headers: Map<String, String> = mapOf(),
        withAuth: Boolean = true
): HttpResponse<T> = fetch(Put, path, body, headers, withAuth)

suspend inline fun <reified T> ApiClient.delete(
        path: String = "",
        body: Any? = null,
        headers: Map<String, String> = mapOf(),
        withAuth: Boolean = true
): HttpResponse<T> = fetch(Delete, path, body, headers, withAuth)

suspend fun ApiClient.postUnit(
        path: String = "",
        body: Any? = null,
        headers: Map<String, String> = mapOf(),
        withAuth: Boolean = true
): UnitHttpResponse = fetchUnit(Post, path, body, headers, withAuth)

suspend fun ApiClient.getUnit(
        path: String = "",
        body: Any? = null,
        headers: Map<String, String> = mapOf(),
        withAuth: Boolean = true
): UnitHttpResponse = fetchUnit(Get, path, body, headers, withAuth)

suspend fun ApiClient.putUnit(
        path: String = "",
        body: Any? = null,
        headers: Map<String, String> = mapOf(),
        withAuth: Boolean = true
): UnitHttpResponse = fetchUnit(Put, path, body, headers, withAuth)

suspend fun ApiClient.deleteUnit(
        path: String = "",
        body: Any? = null,
        headers: Map<String, String> = mapOf(),
        withAuth: Boolean = true
): UnitHttpResponse = fetchUnit(Delete, path, body, headers, withAuth)