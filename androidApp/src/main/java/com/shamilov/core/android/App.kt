package com.shamilov.core.android

import android.app.Application
import android.os.StrictMode
import com.shamilov.core.android.di.androidContext
import com.shamilov.core.di.appModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        enableStrictMode()
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun enableStrictMode() {
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
