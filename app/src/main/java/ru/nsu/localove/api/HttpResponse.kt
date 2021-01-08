package ru.nsu.localove.api

import com.localove.api.ErrorDto

sealed class HttpResponse<T> {

    data class Success<T>(val data: T): HttpResponse<T>()

    data class Error<T>(val error: ErrorDto): HttpResponse<T>()

    fun <R> transform(
        onSuccess: (T) -> R,
        onError: (ErrorDto) -> R
    ): R = when(this) {
        is Success -> onSuccess(data)
        is Error -> onError(error)
    }
}

/**
 * Специализация [HttpResponse] для пустого тела ответа
 */
sealed class UnitHttpResponse {

    object Success: UnitHttpResponse()

    data class Error(val error: ErrorDto): UnitHttpResponse()

    fun <R> transform(
        onSuccess: () -> R,
        onError: (ErrorDto) -> R
    ): R = when(this) {
        is Success -> onSuccess()
        is Error -> onError(error)
    }
}