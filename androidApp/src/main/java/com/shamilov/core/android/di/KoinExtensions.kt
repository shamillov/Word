package com.shamilov.core.android.di

import android.app.Application
import android.content.Context
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.dsl.binds
import org.koin.dsl.module

/**
   Расширение для предоставления контекста в di граф
 */
fun KoinApplication.androidContext(androidContext: Context): KoinApplication {
    if (koin.logger.isAt(Level.INFO)) {
        koin.logger.info("[init] declare Android Context")
    }

    if (androidContext is Application) {
        koin.loadModules(listOf(module {
            single { androidContext } binds arrayOf(Context::class, Application::class)
        }))
    } else {
        koin.loadModules(listOf(module {
            single { androidContext }
        }))
    }

    return this
}
