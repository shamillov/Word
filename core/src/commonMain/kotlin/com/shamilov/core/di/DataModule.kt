package com.shamilov.core.di

import com.shamilov.core.data.db.WordDataStore
import com.shamilov.core.data.repository.WordsRepositoryImpl
import com.shamilov.core.data.repository.WordsRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule: Module = module {
    singleOf(::WordDataStore)

    singleOf(::WordsRepositoryImpl) { bind<WordsRepository>() }
}
