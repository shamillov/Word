package com.shamilov.core.android.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.shamilov.core.android.widget.WordAppWidget.Companion.translationVisibilityKey

/**
 * @author Shamilov on 30.05.2023
 */
class TranslationVisibilityAction : ActionCallback {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        updateAppWidgetState(
            context = context,
            definition = PreferencesGlanceStateDefinition,
            glanceId = glanceId,
        ) {
            it.toMutablePreferences()
                .apply {
                    this[translationVisibilityKey] = true
                }
        }
        WordAppWidget().update(context, glanceId)
    }
}
