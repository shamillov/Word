package com.shamilov.core.android.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import org.koin.core.component.KoinComponent

/**
 * @author Shamilov on 31.05.2023
 */
class ChangeWordAction : ActionCallback, KoinComponent {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {

    }
}