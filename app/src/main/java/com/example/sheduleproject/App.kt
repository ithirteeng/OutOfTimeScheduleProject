package com.example.sheduleproject

import android.app.Application
import com.example.sheduleproject.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    tokenModule,
                    networkModule,
                    loginModule,
                    registrationFirstModule,
                    registrationSecondModule,
                    scheduleChoiceModule,
                    scheduleModule,
                    splashModule
                )
            )
        }
    }
}