package com.shamilov.core.data.repository

import com.shamilov.core.data.db.Word
import com.shamilov.core.data.db.WordsDataStore

/**
 * @author Shamilov on 31.05.2023
 */
interface WidgetRepository {
    fun getWord(): Word?
    fun changeWord(id: Long): Word?
}

internal class WidgetRepositoryImpl(
    private val wordsDataStore: WordsDataStore,
) : WidgetRepository {

    override fun getWord() : Word? {
        return wordsDataStore.getWords()
            .randomOrNull()
    }

    override fun changeWord(id: Long): Word? {
        return wordsDataStore.getWords()
            .filter { id != it.id }
            .randomOrNull()
    }
}
