package com.shamilov.core.android.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shamilov.core.android.R
import com.shamilov.core.android.ui.Screens
import com.shamilov.core.android.ui.cards.CardsScreen
import com.shamilov.core.android.ui.exam.ExamScreen
import com.shamilov.core.android.ui.new_card.NewCardScreen

internal enum class NavigationItem(val label: Int, val icon: ImageVector, val screen: String) {
    CARDS(R.string.navigation_tab_title_cards, Icons.Rounded.Favorite, Screens.CARDS.name),
    EXAM(R.string.navigation_tab_title_exam, Icons.Rounded.PlayArrow, Screens.EXAM.name),
}

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()
    val navigationItems = createNavigationItems()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentScreen = navBackStackEntry?.destination?.route

                navigationItems.forEach { item ->
                    NavigationBarItem(
                        selected = item.screen == currentScreen,
                        onClick = {
                            navController.navigate(item.screen) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
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
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screens.CARDS.name,
            modifier = Modifier.padding(bottom = padding.calculateBottomPadding())
        ) {
            composable(Screens.CARDS.name) {
                CardsScreen(navController, viewModel())
            }
            composable(Screens.EXAM.name) { ExamScreen(viewModel()) }
            composable(Screens.NEW_CARD.name) { NewCardScreen(navController) }
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