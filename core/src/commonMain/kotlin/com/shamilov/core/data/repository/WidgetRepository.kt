package com.shamilov.core.data.repository

import com.shamilov.core.data.db.Word
import com.shamilov.core.data.db.WordsDataStore
import com.shamilov.core.data.local.PreferencesDataStore
import com.shamilov.core.data.local.PreferencesKeys.WIDGET_WORD_ID_KEY

/**
 * @author Shamilov on 31.05.2023
 */
interface WidgetRepository {
    fun getWord(): Word?
    fun changeWord(oldWordId: Long): Word?
}

internal class WidgetRepositoryImpl(
    private val wordsDataStore: WordsDataStore,
    private val preferencesDataStore: PreferencesDataStore,
) : WidgetRepository {

    override fun getWord(): Word? {
        val wordId = preferencesDataStore.getLong(WIDGET_WORD_ID_KEY)

        return if (wordId == -1L) {
            saveAndGetWord()
        } else {
            wordsDataStore.getWordById(wordId)
        }
    }

    override fun changeWord(oldWordId: Long): Word? = wordsDataStore.getRandomWord(oldWordId)

    private fun saveAndGetWord(): Word? {
        val word = wordsDataStore.getRandomWord()
        word?.let { preferencesDataStore.setLong(WIDGET_WORD_ID_KEY, it.id) }
        return word
    }
}
