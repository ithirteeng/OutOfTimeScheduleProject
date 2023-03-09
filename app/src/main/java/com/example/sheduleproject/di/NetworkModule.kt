package com.example.sheduleproject.di

import com.example.sheduleproject.data.common.network.*
import com.example.sheduleproject.data.common.network.authenticator.TokenAuthenticator
import org.koin.dsl.module

val networkModule = module {
    factory { setupLoggingInterceptor() }
    factory { setupNetworkConnectionInterceptor() }
    factory {
        TokenAuthenticator(
            getTokenUseCase = get(),
            saveTokenUseCase = get(),
            refreshTokenUseCase = get()
        )
    }
    factory {
        setupOkHttpClient(
            loggingInterceptor = get(),
            networkConnectionInterceptor = get()
        )
    }
    single { setupRetrofit(okHttpClient = get()) }

    single { setupSplashApi(retrofit = get()) }
    single { setupScheduleApi(retrofit = get()) }
    single { setupLoginApi(retrofit = get()) }
    single { setupRegistrationApi(retrofit = get()) }
    single { setupScheduleChoiceApi(retrofit = get()) }

}