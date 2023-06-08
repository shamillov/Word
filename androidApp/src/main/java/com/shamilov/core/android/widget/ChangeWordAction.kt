package com.shamilov.core.android.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * @author Shamilov on 31.05.2023
 */
internal class ChangeWordAction : ActionCallback, KoinComponent {

    companion object {
        val wordIdKey = ActionParameters.Key<Long>("word_id")
    }

    private val widgetManager: WordWidgetManager by inject()

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val wordId = requireNotNull(parameters[wordIdKey])

        widgetManager.changeWord(glanceId, wordId)
    }
}
