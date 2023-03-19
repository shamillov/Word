package com.shamilov.core.android.ui.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shamilov.core.android.ui.Screen

@Composable
internal fun CardsScreen(
    navController: NavController,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        EmptyState(modifier = Modifier.align(alignment = Alignment.Center)) {
            navController.navigate(Screen.NEW_CARD.name)
        }
    }
}

@Composable
private fun EmptyState(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.filledTonalButtonColors(),
        modifier = modifier
    ) {
        Text(
            text = "Add new word",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview
@Composable
fun CardsScreenPreview() {
//    CardsScreen()
}