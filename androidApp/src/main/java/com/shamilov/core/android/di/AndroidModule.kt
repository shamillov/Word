package com.shamilov.core.android.di

import android.content.Context
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun androidModule(context: Context): Module = module {
    single { context }
}