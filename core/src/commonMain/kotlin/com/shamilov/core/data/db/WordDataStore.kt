package com.shamilov.core.data.db

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

internal class WordDataStore(databaseDriverFactory: WordDatabaseDriverFactory) {
    private val database = WordDatabase.invoke(databaseDriverFactory.createDriver())
    private val dbQuery = database.wordDatabaseQueries

    fun getCards(): List<Card> {
        return dbQuery.selectAllCards().executeAsList()
    }

    fun observeCards(): Flow<List<Card>> {
        return dbQuery.selectAllCards()
            .asFlow()
            .mapToList()
    }

    fun insertCard(
        word: String,
        translation: String,
        category: String?,
        example: String?,
        status: String,
        timestamp: Long,
    ) {
        dbQuery.insertCard(
            word = word,
            translation = translation,
            category = category,
            status = status,
            example = example,
            timestamp = timestamp,
        )
    }

    fun deleteCard(id: Long) {
        dbQuery.deleteCard(id)
    }

    fun clear() {
        dbQuery.clear()
    }
}
