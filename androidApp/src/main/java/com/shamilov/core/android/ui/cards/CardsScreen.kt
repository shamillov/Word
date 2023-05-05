package com.shamilov.core.android.ui.cards

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shamilov.core.android.R
import com.shamilov.core.android.ui.Screen
import com.shamilov.core.android.ui.components.WordCard
import com.shamilov.core.android.ui.theme.spaceM

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CardsScreen(
    navController: NavController,
    viewModel: CardsViewModel,
) {
    val viewState by viewModel.viewState.collectAsState()
    val message = viewModel::accept

    Box(modifier = Modifier.fillMaxSize()) {
        when (viewState.cards.size) {
            0 -> EmptyState(
                modifier = Modifier.align(alignment = Alignment.Center)
            ) { navController.navigate(Screen.NEW_CARD.name) }
            1 -> {
                val firstCard = viewState.cards.first()

                WordCard(
                    card = firstCard,
                    translationVisible = viewState.translationVisible,
                    onEditCardClick = { viewModel.editCard(firstCard.id) },
                    onDeleteCardClick = { viewModel.deleteCard(firstCard.id) },
                    modifier = Modifier.align(alignment = Alignment.Center),
                )
            }
            else -> {
                val pageCount = viewState.cards.size

                // We start the pager in the middle of the raw number of pages
                val startIndex = Int.MAX_VALUE / 2
                val pagerState = rememberPagerState(initialPage = startIndex)

                HorizontalPager(
                    // Set the raw page count to a really large number
                    pageCount = Int.MAX_VALUE,
                    state = pagerState,
                    // Add 32.dp horizontal padding to 'center' the pages
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    // Add some horizontal spacing between items
                    pageSpacing = 16.dp,
                    modifier = Modifier
                        .align(alignment = Alignment.Center),
                    flingBehavior = PagerDefaults.flingBehavior(
                        state = pagerState,
                        lowVelocityAnimationSpec = tween(durationMillis = 500),
                    )
                ) { index ->
                    // We calculate the page from the given index
                    val page = (index - startIndex).floorMod(pageCount)
                    val card = viewState.cards[page]

                    WordCard(
                        card = card,
                        translationVisible = viewState.translationVisible,
                        onEditCardClick = { viewModel.editCard(card.id) },
                        onDeleteCardClick = { viewModel.deleteCard(card.id) },
                    )
                }
            }
        }

        Checkbox(
            checked = viewState.translationVisible,
            onCheckedChange = {
                message(CardsMessage.TranslationVisible(it))
            },
        )

        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(id = R.string.label_add_new_word)) },
            icon = { Icon(imageVector = Icons.Rounded.Add, contentDescription = null) },
            onClick = {
                navController.navigate(Screen.NEW_CARD.name)
            },
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(spaceM)
        )
    }
}

private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}

@Composable
internal fun EmptyState(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val displayWidth = configuration.screenWidthDp

    Card(
        modifier = modifier
            .size(displayWidth.dp)
            .padding(spaceM)
            .clickable { onClick() }
    ) {
        Text(
            text = "Добавьте новую карточку для изучения",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(spaceM)
        )
    }
}

@Preview
@Composable
private fun CardsScreenPreview() {
    CardsScreen(rememberNavController(), viewModel())
}