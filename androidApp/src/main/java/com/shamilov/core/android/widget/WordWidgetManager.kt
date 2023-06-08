package com.shamilov.core.android.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import com.shamilov.core.data.db.Word
import com.shamilov.core.data.repository.WidgetRepository

/**
 * @author Shamilov on 03.06.2023
 */
internal class WordWidgetManager(
    private val repository: WidgetRepository,
    private val context: Context,
) {
    suspend fun initWidget() {
        val word = repository.getWord()
        val widget = GlanceAppWidgetManager(context)
        val glanceIds = widget.getGlanceIds(WordAppWidget::class.java)

        glanceIds.forEach { glanceId ->
            val newState = if (word == null) {
                WordState.Empty("Is empty. Click to add new words")
            } else {
                WordState.Available(mapDataToUi(word))
            }

            updateWidgetState(glanceId, newState)
        }
        WordAppWidget().updateAll(context)
    }

    suspend fun changeWord(glanceId: GlanceId, currentWordId: Long) {
        val newWord = repository.changeWord(currentWordId)

        val newState = if (newWord == null) {
            WordState.Empty("All words have been studied. Click to add new words")
        } else {
            WordState.Available(mapDataToUi(newWord))
        }

        updateWidgetState(glanceId, newState)

        WordAppWidget().update(context, glanceId)
    }

    private fun mapDataToUi(word: Word): WordUiData {
        return WordUiData(
            id = word.id,
            word = word.word,
            translation = word.translation,
        )
    }

    private suspend fun updateWidgetState(glanceId: GlanceId, newState: WordState) {
        updateAppWidgetState(
            context = context,
            definition = WordStateDefinition,
            glanceId = glanceId,
            updateState = { newState },
        )
    }
}
