package com.shamilov.core.data.repository

import com.shamilov.core.data.db.Card
import com.shamilov.core.data.db.WordDataStore
import kotlinx.datetime.Clock

interface CardRepository {
    fun getCards(): List<Card>
    fun insertCard(
        word: String,
        translation: String,
        category: String?,
        example: String?,
        status: String,
    )
    fun deleteCard(id: Long)
    fun clear()
}

internal class CardRepositoryImpl(
    private val dataStore: WordDataStore,
) : CardRepository {
    override fun getCards(): List<Card> {
        return dataStore.getAllCards()
    }

    override fun insertCard(
        word: String,
        translation: String,
        category: String?,
        example: String?,
        status: String,
    ) {
        dataStore.insertCard(
            word = word,
            translation = translation,
            category = category,
            example = example,
            status = status,
            timestamp = Clock.System.now().epochSeconds,
        )
    }

    override fun deleteCard(id: Long) {
        dataStore.deleteCard(id)
    }

    override fun clear() {
        dataStore.clear()
    }
}
