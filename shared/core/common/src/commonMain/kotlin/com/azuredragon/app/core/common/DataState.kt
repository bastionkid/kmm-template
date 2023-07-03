package com.azuredragon.app.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class DataState<out T> {
    object InProgress: DataState<Nothing>()

    data class Success<T>(val data: T): DataState<T>()

    data class Error(
        val errorMessage: String,
        val exception: Throwable? = null
    ): DataState<Nothing>()

}

typealias DataStateFlow<T> = Flow<DataState<T>>

fun <T> Flow<T>.asResult(): DataStateFlow<T> {
    return this
        .map<T, DataState<T>> {
            DataState.Success(it)
        }
        .onStart { emit(DataState.InProgress) }
        .catch { emit(DataState.Error(it.message ?: "", it)) }
}