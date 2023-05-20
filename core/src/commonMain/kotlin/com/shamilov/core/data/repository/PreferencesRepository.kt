package com.shamilov.core.data.repository

import com.shamilov.core.data.local.PreferencesDataStore

interface PreferencesRepository

internal class PreferencesRepositoryImpl(
    private val preferencesDataStore: PreferencesDataStore,
) : PreferencesRepository {

}