package com.shamilov.core.data.db

internal class WordDataStore(databaseDriverFactory: WordDatabaseDriverFactory) {
    private val database = WordDatabase.invoke(databaseDriverFactory.createDriver())
    private val dbQuery = database.wordDatabaseQueries

    fun getAllCards(): List<Card> {
        return dbQuery.selectAllCards(::mapCard).executeAsList()
    }

    fun insertCard(card: Card) {
        dbQuery.insertCard(
            id = card.id,
            word = card.word,
            translation = card.translation,
            category = card.category,
            status = card.status,
            example = card.example,
        )
    }

    fun deleteCard(id: Long) {
        dbQuery.deleteCard(id)
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