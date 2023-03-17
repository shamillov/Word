package com.shamilov.core.data.repository

import com.shamilov.core.data.db.Card
import com.shamilov.core.data.db.WordDataStore

interface CardRepository {
    fun getCards(): List<Card>
    fun insertCard(card: Card)
    fun deleteCard(id: Long)
}

internal class CardRepositoryImpl(
    private val dataStore: WordDataStore,
) : CardRepository {
    override fun getCards(): List<Card> {
        return dataStore.getAllCards()
    }

    override fun insertCard(card: Card) {
        dataStore.insertCard(card)
    }

    override fun deleteCard(id: Long) {
        dataStore.deleteCard(id)
    }
}