package com.universall.appcore.ui.resources

import androidx.compose.runtime.Immutable
import com.universall.appcore.utils.UIString
import com.universall.core.collections.annotations.ConventionImmutable

private const val MAX_STATE_SEARCH_DEPTH = 32

@Immutable
sealed interface ResourceState<@ConventionImmutable out T> {
    // You MUST use @Immutable types for T

    @Immutable
    object Idle : ResourceState<Nothing>

    @Immutable
    sealed interface Fetching<T> : ResourceState<T> {
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
    sealed interface ResultState<T> : ResourceState<T>

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

private fun <T> ResourceState<T>.anyNonErrorResultOrNull(): ResourceState.ResultState<T>? {
    var current: ResourceState<T>? = this
    var depth = 0

    while (current != null && depth++ < MAX_STATE_SEARCH_DEPTH) {
        current = when (current) {
            is ResourceState.Error -> current.lastResultState
            is ResourceState.Fetching -> current.lastResultState
            is ResourceState.Idle -> return null

            is ResourceState.ResultState -> return current
        }
    }

    return null
}

private fun <T> ResourceState<T>.currentOrPreviousResult(): ResourceState.ResultState<T>? = when (this) {
    is ResourceState.ResultState -> this
    is ResourceState.Fetching -> this.lastResultState
    is ResourceState.Idle -> null
}

fun <T> ResourceState<T>.anySuccessOrNull(): ResourceState.Success<T>? {  // TODO: Track visited states?
    var current: ResourceState<T>? = this
    var depth = 0

    while (current != null && depth++ < MAX_STATE_SEARCH_DEPTH) {
        current = when (current) {
            is ResourceState.Error -> current.lastResultState
            is ResourceState.Fetching -> current.lastResultState
            is ResourceState.Idle -> return null

            is ResourceState.Success -> return current
        }
    }

    return null
}

fun <T> ResourceState<T>.anyErrorOrNull(): ResourceState.Error<T>? {
    var current: ResourceState<T>? = this
    var depth = 0

    while (current != null && depth++ < MAX_STATE_SEARCH_DEPTH) {
        when (current) {
            is ResourceState.Fetching -> current = current.lastResultState
            is ResourceState.Success -> return null
            is ResourceState.Idle -> return null

            is ResourceState.Error -> return current
        }
    }

    return null
}

val ResourceState<*>.hasSuccess: Boolean
    get() = when (this) {
        is ResourceState.Success -> true
        is ResourceState.Idle -> false
        is ResourceState.Loading -> anySuccessOrNull() != null
        is ResourceState.Refreshing -> anySuccessOrNull() != null
        is ResourceState.Error -> anySuccessOrNull() != null
    }

val ResourceState<*>.hasError: Boolean
    get() = when (this) {
        is ResourceState.Error -> true
        is ResourceState.Success -> false
        is ResourceState.Idle -> false
        is ResourceState.Loading -> anyErrorOrNull() != null
        is ResourceState.Refreshing -> anyErrorOrNull() != null
    }

fun <T> ResourceState<T>.toSuccess(data: T): ResourceState.Success<T> = ResourceState.Success(data)
fun <T> ResourceState<T>.toRefreshing(): ResourceState.Refreshing<T> = ResourceState.Refreshing(lastResultState = this.currentOrPreviousResult())
fun <T> ResourceState<T>.toLoading(): ResourceState.Loading<T> = ResourceState.Loading(lastResultState = this.currentOrPreviousResult())
fun <T> ResourceState<T>.toError(errorMessage: UIString, keepOnlyLastError: Boolean = true): ResourceState.Error<T> =
    // Setting keepOnlyLastError=false will create endless(limited by MAX_STATE_SEARCH_DEPTH) chain of errors
    // If MAX_STATE_SEARCH_DEPTH overflows, Success result can be deleted (replaced by errors)

    ResourceState.Error(
        errorMessage = errorMessage,
        lastResultState = if (keepOnlyLastError) this.anyNonErrorResultOrNull() else this.currentOrPreviousResult()
    )

val ResourceState<*>.isIdle: Boolean get() = this is ResourceState.Idle
val ResourceState<*>.isLoading: Boolean get() = this is ResourceState.Loading
val ResourceState<*>.isRefreshing: Boolean get() = this is ResourceState.Refreshing
val ResourceState<*>.isSuccess: Boolean get() = this is ResourceState.Success
val ResourceState<*>.isError: Boolean get() = this is ResourceState.Error
val ResourceState<*>.isFetching: Boolean get() = this is ResourceState.Fetching
val ResourceState<*>.isResultState: Boolean get() = this is ResourceState.ResultState

inline fun ResourceState<*>.whenIdle(block: () -> Unit) {
    if (isIdle) block()
}

inline fun ResourceState<*>.whenLoading(block: () -> Unit) {
    if (isLoading) block()
}

inline fun ResourceState<*>.whenLoading(hasAnyData: Boolean, block: () -> Unit) {
    if (isLoading && hasAnyData == hasSuccess) block()
}

inline fun ResourceState<*>.whenLoading(hasAnyData: Boolean, hasAnyError: Boolean, block: () -> Unit) {
    if (isLoading && hasAnyData == hasSuccess && hasAnyError == hasError) block()
}

inline fun ResourceState<*>.whenRefreshing(block: () -> Unit) {
    if (isRefreshing) block()
}

inline fun ResourceState<*>.whenRefreshing(hasAnyData: Boolean, block: () -> Unit) {
    if (isRefreshing && hasAnyData == hasSuccess) block()
}

inline fun ResourceState<*>.whenRefreshing(hasAnyData: Boolean, hasAnyError: Boolean, block: () -> Unit) {
    if (isRefreshing && hasAnyData == hasSuccess && hasAnyError == hasError) block()
}

inline fun ResourceState<*>.whenFetching(block: () -> Unit) {
    if (isFetching) block()
}

inline fun ResourceState<*>.whenFetching(hasAnyData: Boolean, block: () -> Unit) {
    if (isFetching && hasAnyData == hasSuccess) block()
}

inline fun ResourceState<*>.whenFetching(hasAnyData: Boolean, hasAnyError: Boolean, block: () -> Unit) {
    if (isFetching && hasAnyData == hasSuccess && hasAnyError == hasError) block()
}

inline fun ResourceState<*>.whenFetchingFirstTime(block: () -> Unit) {
    whenFetching(hasAnyData = false, block = block)
}

inline fun <T> ResourceState<T>.whenSuccess(block: (T) -> Unit) {
    if (this is ResourceState.Success) block(data)
}

inline fun <T> ResourceState<T>.whenError(block: (state: ResourceState.Error<T>) -> Unit) {
    if (this is ResourceState.Error) block(this)
}

inline fun <T> ResourceState<T>.whenHasAnyData(block: (data: T) -> Unit) {
    anySuccessOrNull()?.let { block(it.data) }
}

inline fun <T> ResourceState<T>.whenHasNoData(block: () -> Unit) {
    if (!hasSuccess) block()
}

inline fun <T> ResourceState<T>.whenHasAnyError(block: (state: ResourceState.Error<T>) -> Unit) {
    anyErrorOrNull()?.let { block(it) }
}

inline fun <T> ResourceState<T>.whenHasNoError(block: () -> Unit) {
    if (!hasError) block()
}
