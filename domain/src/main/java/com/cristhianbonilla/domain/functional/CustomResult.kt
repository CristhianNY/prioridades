package com.cristhianbonilla.domain.functional

import com.cristhianbonilla.domain.functional.CustomResult.Error
sealed class CustomResult<out E, out S> {

    data class Success<out S>(val success: S) : CustomResult<Nothing, S>()
    data class Error<out L>(val error: L) : CustomResult<L, Nothing>()

    val isSuccess get() = this is Success<S>
    val isError get() = this is Error<E>

    fun <R> success(b: R) = Success(b)

    fun <L> error(a: L) = Error(a)

    fun fold(
        fnL: (E) -> Any,
        fnR: (S) -> Any
    ): Any =
        when (this) {
            is Error -> fnL(error)
            is Success -> fnR(success)
        }
}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> CustomResult<L, R>.mapError(fn: (L) -> T): CustomResult<T, R> {
    return when (this) {
        is Error -> Error(fn(error))
        is CustomResult.Success -> CustomResult.Success(success)
    }
}

fun <T, E, S> CustomResult<E, S>.flatMap(fn: (S) -> CustomResult<E, T>): CustomResult<E, T> =
    when (this) {
        is Error -> Error(error)
        is CustomResult.Success -> fn(success)
    }
fun <T, E, S> CustomResult<E, S>.map(fn: (S) -> (T)): CustomResult<E, T> = this.flatMap(fn.c(::success))

fun <E, S> CustomResult<E, S>.getOrElse(value: S): S =
    when (this) {
        is Error -> value
        is CustomResult.Success -> success
    }

fun <E, S> CustomResult<E, S>.getOrNull(): S? =
    when (this) {
        is Error -> null
        is CustomResult.Success -> success
    }