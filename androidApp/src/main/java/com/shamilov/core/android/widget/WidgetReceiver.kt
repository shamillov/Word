package com.shamilov.core.android.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * @author Shamilov on 30.05.2023
 */
internal class WidgetReceiver : GlanceAppWidgetReceiver(), KoinComponent {
    private val wordWidgetManager: WordWidgetManager by inject()

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override val glanceAppWidget: GlanceAppWidget = WordAppWidget()

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
        coroutineScope.cancel()
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)

        requireNotNull(context)

        coroutineScope.launch {
            wordWidgetManager.initWidget()
        }
    }
}
