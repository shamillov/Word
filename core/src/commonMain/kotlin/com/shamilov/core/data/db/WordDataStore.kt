package com.shamilov.core.data.db

internal class WordDataStore(databaseDriverFactory: WordDatabaseDriverFactory) {
    private val database = WordDatabase.invoke(databaseDriverFactory.createDriver())
    private val dbQuery = database.wordDatabaseQueries

    fun getAllCards(): List<Card> {
        return dbQuery.selectAllCards(::mapCard).executeAsList()
    }

    fun insertCard(
        word: String,
        translation: String,
        category: String?,
        example: String?,
        status: String,
    ) {
        dbQuery.insertCard(
            word = word,
            translation = translation,
            category = category,
            status = status,
            example = example,
        )
    }

    fun deleteCard(id: Long) {
        dbQuery.deleteCard(id)
    }

    fun clear() {
        dbQuery.clear()
    }

    private fun mapCard(
        id: Long,
        word: String,
        translation: String,
        category: String?,
        status: String,
        example: String?
    ) = Card(
        id = id,
        word = word,
        translation = translation,
        category = category,
        status = status,
        example = example,
    )
}
