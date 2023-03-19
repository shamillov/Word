package com.shamilov.core.di

import com.shamilov.core.data.db.WordDataStore
import com.shamilov.core.data.repository.CardRepository
import com.shamilov.core.data.repository.CardRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule: Module = module {
    singleOf(::WordDataStore)

    factoryOf(::CardRepositoryImpl) { bind<CardRepository>() }
}

