package com.shamilov.core.android.widget

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.shamilov.core.android.ui.MainActivity
import com.shamilov.core.android.widget.theme.WidgetTheme

/**
 * @author Shamilov on 30.05.2023
 */
internal class WordAppWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = WordStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val state = currentState<WordState>()

            WidgetTheme {
                Widget(state = state)
            }
        }
    }
}

@Composable
private fun Widget(state: WordState) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = GlanceModifier
            .fillMaxSize()
            .background(GlanceTheme.colors.surfaceVariant)
    ) {
        when (state) {
            is WordState.Loading -> CircularProgressIndicator()
            is WordState.Available -> Content(wordOfTheDay = state.word)
            is WordState.Empty -> EmptyState(state.message)
        }
    }
}

@Composable
private fun Content(wordOfTheDay: WordUiData) {
    var translationVisible by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = GlanceModifier.padding(8.dp)
    ) {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .clickable(actionStartActivity<MainActivity>()),
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        ) {
            Text(
                text = wordOfTheDay.word,
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                )
            )

            Spacer(modifier = GlanceModifier.size(8.dp))

            if (translationVisible) {
                Text(
                    text = wordOfTheDay.translation,
                    style = TextStyle(
                        color = GlanceTheme.colors.onSurfaceVariant,
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

        Button(
            text = "Remembered",
            onClick = actionRunCallback<ChangeWordAction>(
                parameters = actionParametersOf(ChangeWordAction.wordIdKey to wordOfTheDay.id)
            ),
        )
    }
}

@Composable
private fun EmptyState(message: String) {
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable(actionStartActivity<MainActivity>()),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = message,
            style = TextStyle(
                color = GlanceTheme.colors.onSurfaceVariant,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                textAlign = TextAlign.Center
            )
        )
    }
}
