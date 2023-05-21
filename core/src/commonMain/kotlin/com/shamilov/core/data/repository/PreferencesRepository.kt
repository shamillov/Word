package com.shamilov.core.data.repository

import com.shamilov.core.data.local.PreferencesDataStore

interface PreferencesRepository {
    fun clear()
}

internal class PreferencesRepositoryImpl(
    private val preferencesDataStore: PreferencesDataStore,
) : PreferencesRepository {
    override fun clear() {
        preferencesDataStore.clear()
    }
}
