package com.example.sheduleproject.di

import com.example.sheduleproject.data.common.network.*
import org.koin.dsl.module

val networkModule = module {
    factory { setupLoggingInterceptor() }
    factory { setupNetworkConnectionInterceptor() }
    factory { setupOkHttpClient(loggingInterceptor = get(), networkConnectionInterceptor = get()) }
    single { setupRetrofit(okHttpClient = get()) }

    single { setupSplashApi(retrofit = get()) }
    single { setupScheduleApi(retrofit = get()) }
    single { setupLoginApi(retrofit = get()) }
    single { setupRegistrationApi(retrofit = get()) }
    single { setupScheduleChoiceApi(retrofit = get()) }

}