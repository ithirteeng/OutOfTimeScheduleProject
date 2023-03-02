package com.example.sheduleproject.di

import com.example.sheduleproject.data.common.network.*
import org.koin.dsl.module

val networkModule = module {
    factory { setupLoggingInterceptor() }
    factory { setupNetworkConnectionInterceptor() }
    factory { setupOkHttpClient(loggingInterceptor = get(), networkConnectionInterceptor = get()) }
    factory { setupSplashApi(retrofit = get()) }
    single { setupRetrofit(okHttpClient = get()) }
}