package com.shamilov.core.android.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shamilov.core.android.ui.theme.sizeM

internal data class CardItem(
    val id: Long,
    val word: String,
    val translation: String,
    val backgroundColor: Color,
    val category: String? = null,
    val example: String? = null,
)

@Composable
internal fun WordCard(cardItem: CardItem) {
    val configuration = LocalConfiguration.current
    val displayWidth = configuration.screenWidthDp

    Card(
        colors = CardDefaults.cardColors(cardItem.backgroundColor),
        modifier = Modifier
            .size(displayWidth.dp)
            .padding(16.dp)
    ) {
        Text(
            text = cardItem.word,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(sizeM)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
@Preview
fun WordCardPreview() {
    WordCard(
        cardItem = CardItem(
            id = 0,
            word = "Hello",
            translation = "Привет",
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        )
    )
}