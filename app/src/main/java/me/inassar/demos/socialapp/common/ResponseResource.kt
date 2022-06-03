package me.inassar.demos.socialapp.common

/**
 * Response resource
 * This class is used as a data wrapper to help passing data
 * between business layers with knowing it's state.
 * @param T
 * @constructor Create empty Response resource
 */
sealed class ResponseResource<T> {
    data class Success<T>(val data: T) : ResponseResource<T>()
    data class Error<T>(val errorMessage: T) : ResponseResource<T>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> error(errorMessage: T) = Error<T>(errorMessage)
    }
}
