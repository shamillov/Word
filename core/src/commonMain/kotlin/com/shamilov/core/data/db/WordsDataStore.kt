package com.shamilov.core.data.db

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

internal class WordsDataStore(databaseDriverFactory: WordDatabaseDriverFactory) {
    private val database = WordDatabase.invoke(databaseDriverFactory.createDriver())
    private val dbQuery = database.wordDatabaseQueries

    fun getWords(): List<Word> {
        return dbQuery.selectAllWords().executeAsList()
    }

    fun observeWords(): Flow<List<Word>> {
        return dbQuery.selectAllWords()
            .asFlow()
            .mapToList()
    }

    fun insertWord(
        word: String,
        translation: String,
        category: String?,
        example: String?,
        status: String,
        timestamp: Long,
    ) {
        dbQuery.insertWord(
            word = word,
            translation = translation,
            category = category,
            status = status,
            example = example,
            timestamp = timestamp,
        )
    }

    fun deleteWord(id: Long) {
        dbQuery.deleteWord(id)
    }

    fun clear() {
        dbQuery.clear()
    }
}
