package com.shamilov.core.data.db

import com.shamilov.core.data.Constants
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class WordDatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(WordDatabase.Schema, Constants.DATABASE_NAME)
    }
}
