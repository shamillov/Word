package com.shamilov.core.data.db

import com.squareup.sqldelight.db.SqlDriver

expect class WordDatabaseDriverFactory {
    fun createDriver(): SqlDriver
}
