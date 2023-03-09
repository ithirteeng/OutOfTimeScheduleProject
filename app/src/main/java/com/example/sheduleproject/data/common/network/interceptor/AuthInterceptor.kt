package com.example.sheduleproject.data.common.network.interceptor

import com.example.sheduleproject.domain.token.usecase.GetTokenUseCase
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val getTokenUseCase: GetTokenUseCase
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = getTokenUseCase()
        return if (token.accessToken != null) {
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer ${token.accessToken}")
                .build()
            chain.proceed(request)
        } else {
            var request = chain.request()
            request = request.newBuilder()
                .build()
            chain.proceed(request)
        }
    }
}