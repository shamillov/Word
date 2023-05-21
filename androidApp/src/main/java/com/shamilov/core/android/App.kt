package com.shamilov.core.android

import android.app.Application
import android.os.StrictMode
import com.shamilov.core.android.di.androidModule
import com.shamilov.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        enableStrictMode()
        super.onCreate()

        initDI()
    }

    private fun initDI() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(appModule + androidModule)
        }
    }

    private fun enableStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode
                .setVmPolicy(
                    StrictMode.VmPolicy.Builder()
                        .detectActivityLeaks()
                        .detectLeakedSqlLiteObjects()
                        .penaltyLog()
                        .build()
                )
            StrictMode
                .setThreadPolicy(
                    StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads()
                        .detectDiskWrites()
                        .penaltyLog()
                        .build()
                )
        }
    }
}
