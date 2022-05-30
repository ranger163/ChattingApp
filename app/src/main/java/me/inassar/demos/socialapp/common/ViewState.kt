package me.inassar.demos.socialapp.common

/**
 * View state
 * This class is used as a data wrapper to help
 * passing data between presentation layers knowing it's state.
 * @param T
 * @constructor Create empty View state
 */
sealed class ViewState<T> {
    class Loading<T> : ViewState<T>()
    class Success<T>(val data: T) : ViewState<T>()
    class Error<T>(val errorMessage: String) : ViewState<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> error(errorMessage: String) = Error<T>(errorMessage)
    }
}
