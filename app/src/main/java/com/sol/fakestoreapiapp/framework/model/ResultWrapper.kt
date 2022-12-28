package com.sol.fakestoreapiapp.framework.model

sealed class ResultWrapper<out T> {

    data class Success<T>(val data: T) : ResultWrapper<T>()

    data class Error(val exception: Throwable? = null) : ResultWrapper<Nothing>()

    object Loading : ResultWrapper<Nothing>()

    fun isSuccessful(): Boolean{
        return this is Success
    }

    fun isFailed(): Boolean{
        return this is Error
    }

    fun isLoading(): Boolean{
        return this is Loading
    }

    fun isEmpty(): Boolean{
        if (this is Success){
            return if (this.data == null){
                true
            }else
                this.data is List<*> && this.data.isEmpty()
        }

        return true
    }
}

fun <T> ResultWrapper<T>.getData(): T?{
    if (this is ResultWrapper.Success)
        return this.data

    return null
}

fun <T> ResultWrapper<T>.getThrowable(): Throwable?{
    if (this is ResultWrapper.Error)
        return this.exception

    return null
}