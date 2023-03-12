package com.example.sheduleproject.data.common.network

import com.example.sheduleproject.BuildConfig
import com.example.sheduleproject.data.common.network.interceptor.NetworkConnectionInterceptor
import com.example.sheduleproject.data.entrance.login.api.LoginApi
import com.example.sheduleproject.data.entrance.registration.api.RegistrationApi
import com.example.sheduleproject.data.schedule.api.ScheduleApi
import com.example.sheduleproject.data.schedulechoice.api.ScheduleChoiceApi
import com.example.sheduleproject.data.splash.api.SplashApi
import com.example.sheduleproject.data.token.api.TokenApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun setupLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return loggingInterceptor
}

fun setupNetworkConnectionInterceptor(): NetworkConnectionInterceptor =
    NetworkConnectionInterceptor()


fun setupRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun setupSplashApi(retrofit: Retrofit): SplashApi = retrofit.create(SplashApi::class.java)

fun setupScheduleApi(retrofit: Retrofit): ScheduleApi = retrofit.create(ScheduleApi::class.java)

fun setupLoginApi(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)

fun setupRegistrationApi(retrofit: Retrofit): RegistrationApi =
    retrofit.create(RegistrationApi::class.java)

fun setupScheduleChoiceApi(retrofit: Retrofit): ScheduleChoiceApi =
    retrofit.create(ScheduleChoiceApi::class.java)

fun setupTokenApi(retrofit: Retrofit): TokenApi =
    retrofit.create(TokenApi::class.java)

