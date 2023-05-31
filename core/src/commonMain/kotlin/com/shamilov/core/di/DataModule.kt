package com.shamilov.core.di

import com.shamilov.core.data.db.WordsDataStore
import com.shamilov.core.data.repository.PreferencesRepository
import com.shamilov.core.data.repository.PreferencesRepositoryImpl
import com.shamilov.core.data.repository.UserRepository
import com.shamilov.core.data.repository.UserRepositoryImpl
import com.shamilov.core.data.repository.WidgetRepository
import com.shamilov.core.data.repository.WidgetRepositoryImpl
import com.shamilov.core.data.repository.WordsRepositoryImpl
import com.shamilov.core.data.repository.WordsRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * @author Shamilov on 31.05.2023
 */
val dataModule: Module = module {
    singleOf(::WordsDataStore)

    // repositories
    singleOf(::WordsRepositoryImpl) { bind<WordsRepository>() }
    singleOf(::PreferencesRepositoryImpl) { bind<PreferencesRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::WidgetRepositoryImpl) { bind<WidgetRepository>() }
}
