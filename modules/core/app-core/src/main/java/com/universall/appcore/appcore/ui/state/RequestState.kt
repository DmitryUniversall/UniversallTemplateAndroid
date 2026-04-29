package com.universall.appcore.appcore.ui.state

import androidx.compose.runtime.Immutable
import com.universall.appcore.appcore.ui.utils.UIString
import com.universall.core.collections.annotations.ConventionImmutable

private const val MAX_STATE_SEARCH_DEPTH = 32

@Immutable
sealed interface RequestState<@ConventionImmutable out T> {
    // You MUST use @Immutable types for T

    @Immutable
    object Idle : RequestState<Nothing>

    @Immutable
    sealed interface Fetching<T> : RequestState<T> {
        val lastResultState: ResultState<T>?
    }

    @Immutable
    class Loading<T> internal constructor(
        override val lastResultState: ResultState<T>? = null
    ) : Fetching<T>

    @Immutable
    class Refreshing<T> internal constructor(
        override val lastResultState: ResultState<T>? = null
    ) : Fetching<T>

    @Immutable
    sealed interface ResultState<T> : RequestState<T>

    @Immutable
    class Success<T> internal constructor(
        val data: T
    ) : ResultState<T>

    @Immutable
    class Error<T> internal constructor(
        val errorMessage: UIString,
        val lastResultState: ResultState<T>? = null
    ) : ResultState<T>
}

private fun <T> RequestState<T>.anyNonErrorResultOrNull(): RequestState.ResultState<T>? {
    var current: RequestState<T>? = this
    var depth = 0

    while (current != null && depth++ < MAX_STATE_SEARCH_DEPTH) {
        current = when (current) {
            is RequestState.Error -> current.lastResultState
            is RequestState.Fetching -> current.lastResultState
            is RequestState.Idle -> return null

            is RequestState.ResultState -> return current
        }
    }

    return null
}

private fun <T> RequestState<T>.currentOrPreviousResult(): RequestState.ResultState<T>? = when (this) {
    is RequestState.ResultState -> this
    is RequestState.Fetching -> this.lastResultState
    is RequestState.Idle -> null
}

fun <T> RequestState<T>.anySuccessOrNull(): RequestState.Success<T>? {  // TODO: Track visited states?
    var current: RequestState<T>? = this
    var depth = 0

    while (current != null && depth++ < MAX_STATE_SEARCH_DEPTH) {
        current = when (current) {
            is RequestState.Error -> current.lastResultState
            is RequestState.Fetching -> current.lastResultState
            is RequestState.Idle -> return null

            is RequestState.Success -> return current
        }
    }

    return null
}

fun <T> RequestState<T>.anyErrorOrNull(): RequestState.Error<T>? {
    var current: RequestState<T>? = this
    var depth = 0

    while (current != null && depth++ < MAX_STATE_SEARCH_DEPTH) {
        when (current) {
            is RequestState.Fetching -> current = current.lastResultState
            is RequestState.Success -> return null
            is RequestState.Idle -> return null

            is RequestState.Error -> return current
        }
    }

    return null
}

val RequestState<*>.hasSuccess: Boolean
    get() = when (this) {
        is RequestState.Success -> true
        is RequestState.Idle -> false
        is RequestState.Loading -> anySuccessOrNull() != null
        is RequestState.Refreshing -> anySuccessOrNull() != null
        is RequestState.Error -> anySuccessOrNull() != null
    }

val RequestState<*>.hasError: Boolean
    get() = when (this) {
        is RequestState.Error -> true
        is RequestState.Success -> false
        is RequestState.Idle -> false
        is RequestState.Loading -> anyErrorOrNull() != null
        is RequestState.Refreshing -> anyErrorOrNull() != null
    }

fun <T> RequestState<T>.toSuccess(data: T): RequestState.Success<T> = RequestState.Success(data)
fun <T> RequestState<T>.toRefreshing(): RequestState.Refreshing<T> = RequestState.Refreshing(lastResultState = this.currentOrPreviousResult())
fun <T> RequestState<T>.toLoading(): RequestState.Loading<T> = RequestState.Loading(lastResultState = this.currentOrPreviousResult())
fun <T> RequestState<T>.toError(errorMessage: UIString, keepOnlyLastError: Boolean = true): RequestState.Error<T> =
// Setting keepOnlyLastError=false will create endless(limited by MAX_STATE_SEARCH_DEPTH) chain of errors
    // If MAX_STATE_SEARCH_DEPTH overflows, Success result can be deleted (replaced by errors)

    RequestState.Error(
        errorMessage = errorMessage,
        lastResultState = if (keepOnlyLastError) this.anyNonErrorResultOrNull() else this.currentOrPreviousResult()
    )

val RequestState<*>.isIdle: Boolean get() = this is RequestState.Idle
val RequestState<*>.isLoading: Boolean get() = this is RequestState.Loading
val RequestState<*>.isRefreshing: Boolean get() = this is RequestState.Refreshing
val RequestState<*>.isSuccess: Boolean get() = this is RequestState.Success
val RequestState<*>.isError: Boolean get() = this is RequestState.Error
val RequestState<*>.isFetching: Boolean get() = this is RequestState.Fetching
val RequestState<*>.isResultState: Boolean get() = this is RequestState.ResultState

inline fun RequestState<*>.whenIdle(block: () -> Unit) {
    if (isIdle) block()
}

inline fun RequestState<*>.whenLoading(block: () -> Unit) {
    if (isLoading) block()
}

inline fun RequestState<*>.whenLoading(hasAnyData: Boolean, block: () -> Unit) {
    if (isLoading && hasAnyData == hasSuccess) block()
}

inline fun RequestState<*>.whenLoading(hasAnyData: Boolean, hasAnyError: Boolean, block: () -> Unit) {
    if (isLoading && hasAnyData == hasSuccess && hasAnyError == hasError) block()
}

inline fun RequestState<*>.whenRefreshing(block: () -> Unit) {
    if (isRefreshing) block()
}

inline fun RequestState<*>.whenRefreshing(hasAnyData: Boolean, block: () -> Unit) {
    if (isRefreshing && hasAnyData == hasSuccess) block()
}

inline fun RequestState<*>.whenRefreshing(hasAnyData: Boolean, hasAnyError: Boolean, block: () -> Unit) {
    if (isRefreshing && hasAnyData == hasSuccess && hasAnyError == hasError) block()
}

inline fun RequestState<*>.whenFetching(block: () -> Unit) {
    if (isFetching) block()
}

inline fun RequestState<*>.whenFetching(hasAnyData: Boolean, block: () -> Unit) {
    if (isFetching && hasAnyData == hasSuccess) block()
}

inline fun RequestState<*>.whenFetching(hasAnyData: Boolean, hasAnyError: Boolean, block: () -> Unit) {
    if (isFetching && hasAnyData == hasSuccess && hasAnyError == hasError) block()
}

inline fun RequestState<*>.whenFetchingFirstTime(block: () -> Unit) {
    whenFetching(hasAnyData = false, block = block)
}

inline fun <T> RequestState<T>.whenSuccess(block: (T) -> Unit) {
    if (this is RequestState.Success) block(data)
}

inline fun <T> RequestState<T>.whenError(block: (state: RequestState.Error<T>) -> Unit) {
    if (this is RequestState.Error) block(this)
}

inline fun <T> RequestState<T>.whenHasAnyData(block: (data: T) -> Unit) {
    anySuccessOrNull()?.let { block(it.data) }
}

inline fun <T> RequestState<T>.whenHasNoData(block: () -> Unit) {
    if (!hasSuccess) block()
}

inline fun <T> RequestState<T>.whenHasAnyError(block: (state: RequestState.Error<T>) -> Unit) {
    anyErrorOrNull()?.let { block(it) }
}

inline fun <T> RequestState<T>.whenHasNoError(block: () -> Unit) {
    if (!hasError) block()
}
