package ru.nsu.localove.core.nearbyusers

sealed class PictureState {
    class Valid(val bytes: ByteArray) : PictureState()
    object NotFound : PictureState()
}