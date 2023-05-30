package com.shamilov.core.android.ui.cards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shamilov.core.android.R
import com.shamilov.core.android.ui.Screens
import com.shamilov.core.android.ui.components.WordCard
import com.shamilov.core.android.ui.theme.spaceM
import com.shamilov.core.android.ui.utils.boundedClickable
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CardsScreen(
    navController: NavController,
    viewModel: CardsViewModel = koinViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        Settings(modifier = Modifier.align(alignment = Alignment.TopEnd))

        when (viewState.cards.size) {
            0 -> EmptyState(
                modifier = Modifier.align(alignment = Alignment.Center)
            ) { navController.navigate(Screens.NEW_CARD.name) }

            1 -> {
                val card = viewState.cards.first()

                WordCard(
                    card = card,
                    onCardClick = { viewModel.changeTranslationVisible(card) },
                    onEditCardClick = { viewModel.editCard(card.id) },
                    onDeleteCardClick = { viewModel.deleteCard(card.id) },
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
                        onCardClick = { viewModel.changeTranslationVisible(card) },
                        onEditCardClick = { viewModel.editCard(card.id) },
                        onDeleteCardClick = { viewModel.deleteCard(card.id) },
                    )
                }
            }
        }

        Checkbox(
            checked = viewState.cardsTranslationVisible,
            onCheckedChange = {
                viewModel.changeAllTranslationVisible(it)
            },
        )

        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(id = R.string.label_add_new_word)) },
            icon = { Icon(imageVector = Icons.Rounded.Add, contentDescription = null) },
            onClick = {
                navController.navigate(Screens.NEW_CARD.name)
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
private fun Settings(
    modifier: Modifier = Modifier,
) {
    var clickOnIcon by remember { mutableStateOf(false) }
    val animateIconRotation by animateFloatAsState(targetValue = if (clickOnIcon) 90f else 0f)

    Icon(
        imageVector = Icons.Rounded.Settings,
        contentDescription = null,
        modifier = modifier
            .padding(16.dp)
            .rotate(animateIconRotation)
            .boundedClickable {
                clickOnIcon = !clickOnIcon
            }
    )
}

@Composable
private fun EmptyState(
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