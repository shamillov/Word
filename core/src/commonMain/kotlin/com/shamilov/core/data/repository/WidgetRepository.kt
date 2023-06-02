package com.shamilov.core.data.repository

import com.shamilov.core.data.db.Word
import com.shamilov.core.data.db.WordsDataStore

/**
 * @author Shamilov on 31.05.2023
 */
interface WidgetRepository {
    fun getWord(): Word?
    fun getWordById(id: Long): Word?
    fun changeWord(oldWordId: Long): Word?
}

internal class WidgetRepositoryImpl(
    private val wordsDataStore: WordsDataStore,
) : WidgetRepository {

    override fun getWord(): Word? = wordsDataStore.getRandomWord()

    override fun getWordById(id: Long): Word? = wordsDataStore.getWordById(id)

    override fun changeWord(oldWordId: Long): Word? = wordsDataStore.getRandomWord(oldWordId)
}
