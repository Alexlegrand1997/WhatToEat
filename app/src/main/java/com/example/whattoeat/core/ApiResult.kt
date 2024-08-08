package com.example.whattoeat.core

sealed class ApiResult<out R> {
    data class Success<out R>(val data:R) : ApiResult<R>()
    data class Error(val throwable: Throwable) : ApiResult<Nothing>()
    data object Loading: ApiResult<Nothing>()
}

sealed class DataStoreResult<out R>{
    data class Success<out R>(val data:R) : DataStoreResult<R>()
    data class Error(val throwable: Throwable): DataStoreResult<Nothing>()
    data object Loading : DataStoreResult<Nothing>()
}