package com.example.sheduleproject.di

import com.example.sheduleproject.data.common.network.*
import com.example.sheduleproject.data.common.network.authenticator.TokenAuthenticator
import com.example.sheduleproject.data.common.network.interceptor.AuthInterceptor
import com.example.sheduleproject.data.common.network.okhttp.setupOkHttpClient
import com.example.sheduleproject.data.common.network.okhttp.setupTokenOkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    factory { setupLoggingInterceptor() }
    factory { setupNetworkConnectionInterceptor() }

    single(named(SIMP_NETWORK_TOOLS)) {
        setupOkHttpClient(
            loggingInterceptor = get(),
            networkConnectionInterceptor = get(),
        )
    }

    factory(named(TOKEN_NETWORK_TOOLS)) {
        setupTokenOkHttpClient(
            tokenAuthenticator = get(),
            authInterceptor = get(),
            loggingInterceptor = get(),
            networkConnectionInterceptor = get()
        )
    }

    single(named(SIMP_NETWORK_TOOLS)) { setupRetrofit(okHttpClient = get(named(SIMP_NETWORK_TOOLS))) }
    single(named(TOKEN_NETWORK_TOOLS)) { setupRetrofit(okHttpClient = get(named(TOKEN_NETWORK_TOOLS))) }

    single { setupSplashApi(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }
    single { setupScheduleApi(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }
    single { setupLoginApi(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }
    single { setupRegistrationApi(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }
    single { setupScheduleChoiceApi(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }
    single { setupTokenApi(retrofit = get(named(SIMP_NETWORK_TOOLS))) }

    factory {
        TokenAuthenticator(
            getTokenFromLocalStorageUseCase = get(),
            saveTokenToLocalStorageUseCase = get(),
            refreshTokenUseCase = get()
        )
    }

    factory { AuthInterceptor(getTokenFromLocalStorageUseCase = get()) }
}