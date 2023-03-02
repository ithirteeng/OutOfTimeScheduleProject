package com.example.sheduleproject.data.common.network

import com.example.sheduleproject.BuildConfig
import com.example.sheduleproject.data.common.network.interceptor.NetworkConnectionInterceptor
import com.example.sheduleproject.data.splash.api.SplashApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun setupLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return loggingInterceptor
}

fun setupNetworkConnectionInterceptor(): NetworkConnectionInterceptor =
    NetworkConnectionInterceptor()

fun setupOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    networkConnectionInterceptor: NetworkConnectionInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(networkConnectionInterceptor)
        .addNetworkInterceptor(loggingInterceptor)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

fun setupRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun setupSplashApi(retrofit: Retrofit): SplashApi = retrofit.create(SplashApi::class.java)
