package com.shamilov.core.data.local

import android.content.Context
import com.shamilov.core.data.Constants.PREFERENCES_NAME

/**
 * @author Shamilov on 31.05.2023
 */
actual class PreferencesDataStore(
    context: Context,
) {
    private val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    actual fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    actual fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    actual fun getString(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    actual fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    actual fun getLong(key: String): Long {
        return sharedPreferences.getLong(key,-1L)
    }

    actual fun setLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    actual fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    actual fun clearValue(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}