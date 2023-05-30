package com.shamilov.core.android.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shamilov.core.android.R
import com.shamilov.core.android.ui.theme.size3XL
import com.shamilov.core.android.ui.theme.spaceM
import com.shamilov.core.android.ui.theme.spaceXS
import com.shamilov.core.android.ui.utils.boundedClickable

internal data class CardItem(
    val id: Long,
    val word: String,
    val translation: String,
    val status: String,
    val category: String? = null,
    val example: String? = null,
    var translationVisible: Boolean = false,
)

@Composable
internal fun WordCard(
    card: CardItem,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
    onEditCardClick: () -> Unit,
    onDeleteCardClick: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val displayWidth = configuration.screenWidthDp.dp - size3XL

    var exposedMenu by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .size(displayWidth)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onCardClick() }
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(spaceXS)
            ) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = null,
                    modifier = Modifier
                        .boundedClickable { exposedMenu = true }
                )

                DropdownMenu(
                    expanded = exposedMenu,
                    onDismissRequest = { exposedMenu = false },
                ) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.label_action_edit)) },
                        onClick = {
                            onEditCardClick()
                            exposedMenu = false
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Edit,
                                contentDescription = null,
                            )
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.label_action_delete)) },
                        onClick = {
                            onDeleteCardClick()
                            exposedMenu = false
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = null,
                            )
                        }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = Alignment.Center)
                    .padding(spaceM)
            ) {
                Text(
                    text = card.word,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
                AnimatedVisibility(
                    visible = card.translationVisible,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = card.translation,
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun WordCardPreview() {
    WordCard(
        card = CardItem(
            id = 0,
            word = "hello",
            translation = "привет",
            status = "new",
        ),
        onCardClick = {},
        onEditCardClick = {},
        onDeleteCardClick = {},
    )
}