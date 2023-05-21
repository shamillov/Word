package com.shamilov.core.data.local

expect class PreferencesDataStore {
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun setBoolean(key: String, value: Boolean)
    fun getString(key: String, defaultValue: String? = null): String?
    fun setString(key: String, value: String)
    fun clearValue(key: String)
    fun clear()
}
