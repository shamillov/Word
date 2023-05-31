package com.shamilov.core.data.repository

import com.shamilov.core.data.db.Word
import com.shamilov.core.data.db.WordsDataStore

/**
 * @author Shamilov on 31.05.2023
 */
interface WidgetRepository {
    fun getWordOfTheDay(): Word
}

internal class WidgetRepositoryImpl(
    private val wordsDataStore: WordsDataStore,
) : WidgetRepository {
    override fun getWordOfTheDay() : Word {
        return wordsDataStore.getWords().random()
    }
}
