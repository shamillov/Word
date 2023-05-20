package com.shamilov.core.data.repository

import com.shamilov.core.data.db.Word
import com.shamilov.core.data.db.WordDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock

interface WordsRepository {
    fun getWords(): List<Word>
    fun observeWords(): Flow<List<Word>>
    fun insertWord(
        word: String,
        translation: String,
        category: String?,
        example: String?,
        status: String,
    )
    fun deleteWord(id: Long)
    fun clear()
}

internal class WordsRepositoryImpl(
    private val dataStore: WordDataStore,
) : WordsRepository {

    override fun getWords(): List<Word> {
        return dataStore.getWords()
    }

    override fun observeWords(): Flow<List<Word>> {
        return dataStore.observeWords()
    }

    override fun insertWord(
        word: String,
        translation: String,
        category: String?,
        example: String?,
        status: String,
    ) {
        dataStore.insertWord(
            word = word,
            translation = translation,
            category = category,
            example = example,
            status = status,
            timestamp = Clock.System.now().epochSeconds,
        )
    }

    override fun deleteWord(id: Long) {
        dataStore.deleteWord(id)
    }

    override fun clear() {
        dataStore.clear()
    }
}
