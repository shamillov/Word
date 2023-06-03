package com.shamilov.core.android.widget

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.color.isNightMode
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.shamilov.core.android.ui.MainActivity
import com.shamilov.core.android.widget.theme.WidgetTheme
import com.shamilov.core.data.db.Word
import com.shamilov.core.data.repository.WidgetRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * @author Shamilov on 30.05.2023
 */
internal class WordAppWidget : GlanceAppWidget(), KoinComponent {

    companion object {
        val translationVisibilityKey = booleanPreferencesKey("visibility")
    }

    private val widgetRepository: WidgetRepository by inject()

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val wordOfTheDay = widgetRepository.getWord()

        provideContent {
            WidgetTheme(darkTheme = context.isNightMode) {
                Widget(wordOfTheDay = wordOfTheDay)
            }
        }
    }
}

@Composable
private fun Widget(wordOfTheDay: Word?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = GlanceModifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .appWidgetBackground()
    ) {
        if (wordOfTheDay == null) {
            EmptyState()
        } else {
            Content(wordOfTheDay)
        }
    }
}

@Composable
private fun Content(wordOfTheDay: Word) {
    var translationVisible by remember { mutableStateOf(false) }

    Box {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(16.dp)
                .clickable(actionStartActivity<MainActivity>()),
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        ) {
            Text(
                text = wordOfTheDay.word,
                style = TextStyle(
                    color = ColorProvider(MaterialTheme.colorScheme.onSurfaceVariant),
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                )
            )

            Spacer(modifier = GlanceModifier.size(8.dp))

            if (translationVisible) {
                Text(
                    text = wordOfTheDay.translation,
                    style = TextStyle(
                        color = ColorProvider(MaterialTheme.colorScheme.onSurfaceVariant),
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    ),
                )
            }

            Spacer(modifier = GlanceModifier.size(8.dp))

            Button(
                text = "Show translation",
                onClick = { translationVisible = !translationVisible }
            )
        }
    }
}

@Composable
private fun EmptyState() {
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable(actionStartActivity<MainActivity>()),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Is empty. Click to add a new word",
            style = TextStyle(
                color = ColorProvider(MaterialTheme.colorScheme.onSurfaceVariant),
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                textAlign = TextAlign.Center
            )
        )
    }
}
