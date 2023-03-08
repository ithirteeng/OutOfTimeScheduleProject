package com.example.sheduleproject.data.common.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return try {
            chain.proceed(request)
        } catch (e: IOException) {
            handleError()
        }
    }

    private fun handleError(): Nothing {
        throw NoConnectivityException()
    }


}

class NoConnectivityException : IOException() {

    companion object {
        const val ERROR_CODE = 1309
    }

    override val message: String
        get() = "No Internet Connection"
}