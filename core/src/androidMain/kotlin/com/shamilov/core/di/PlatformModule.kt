package com.shamilov.core.di

import com.shamilov.core.data.db.WordDatabaseDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::WordDatabaseDriverFactory)
}