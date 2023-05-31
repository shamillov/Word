package com.shamilov.core.data.db

import android.content.Context
import com.shamilov.core.data.Constants
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class WordDatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(WordDatabase.Schema, context, Constants.DATABASE_NAME)
    }
}
