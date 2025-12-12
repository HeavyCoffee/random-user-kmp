package com.heavycoffee.randomuser

import android.app.Application
import com.heavycoffee.shared.di.DI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DI.start {
            androidContext(this@App)
            androidLogger(level = Level.DEBUG)
        }
    }
}