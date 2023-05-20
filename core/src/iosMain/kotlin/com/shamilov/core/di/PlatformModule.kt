package com.shamilov.core.di

import com.shamilov.core.data.db.WordDatabaseDriverFactory
import com.shamilov.core.data.local.PreferencesDataStore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::WordDatabaseDriverFactory)
    singleOf(::PreferencesDataStore)
}
