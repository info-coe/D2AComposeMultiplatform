package org.infomericainc.infod2a.util



sealed class Resource<T>(
    val data: T? = null,
    val message: String = "",
) {
    class Loading<T> : Resource<T>()
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Success<T>(data: T? = null) : Resource<T>(data)
}