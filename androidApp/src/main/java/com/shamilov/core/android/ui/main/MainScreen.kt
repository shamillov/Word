package com.shamilov.core.android.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shamilov.core.android.R
import com.shamilov.core.android.ui.cards.CardsScreen
import com.shamilov.core.android.ui.exam.ExamScreen

internal enum class NavigationItem(val label: Int, val icon: ImageVector, val screen: String) {
    CARDS(R.string.navigation_tab_title_cards, Icons.Rounded.Favorite, "cards"),
    EXAM(R.string.navigation_tab_title_exam, Icons.Rounded.PlayArrow, "exam"),
}

@Composable
internal fun MainScreen() {
    val navigationItems = createNavigationItems()
    var selectedItem by remember { mutableStateOf(NavigationItem.CARDS) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navigationItems.forEach { item ->
                    NavigationBarItem(
                        selected = item == selectedItem,
                        onClick = { selectedItem = item },
                        label = { Text(text = stringResource(id = item.label)) },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = stringResource(id = item.label),
                            )
                        },
                    )
                }
            }
        }
    ) {
        when (selectedItem) {
            NavigationItem.CARDS -> CardsScreen()
            NavigationItem.EXAM -> ExamScreen()
        }
    }
}

private fun createNavigationItems(): List<NavigationItem> {
    return listOf(
        NavigationItem.CARDS,
        NavigationItem.EXAM,
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}