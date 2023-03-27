package com.shamilov.core.android.ui.new_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shamilov.core.android.R
import com.shamilov.core.android.ui.cards.CardsViewModel
import com.shamilov.core.android.ui.components.Toolbar
import com.shamilov.core.android.ui.theme.sizeM
import com.shamilov.core.android.ui.theme.sizeXS
import com.shamilov.core.android.ui.theme.spaceM
import com.shamilov.core.android.ui.utils.DefaultSpacer
import com.shamilov.core.android.ui.utils.boundedClickable

@Composable
fun NewCardScreen(
    navController: NavController,
    cardsViewModel: CardsViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        var word by remember { mutableStateOf("") }
        var translate by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }
        var example by remember { mutableStateOf("") }

        var wordIsEmptyError by remember { mutableStateOf(false) }
        var translationIsEmptyError by remember { mutableStateOf(false) }

        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Toolbar(title = stringResource(id = R.string.toolbar_title_new_card)) { navController.navigateUp() }
            OutlinedTextField(
                value = word,
                onValueChange = { word = it },
                label = { Text(text = stringResource(id = R.string.label_enter_word)) },
                singleLine = true,
                isError = wordIsEmptyError,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.boundedClickable {
                            word = ""
                        }
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = sizeM),
            )

            DefaultSpacer(space = sizeXS)

            OutlinedTextField(
                value = translate,
                onValueChange = { translate = it },
                label = { Text(text = stringResource(id = R.string.label_enter_translation)) },
                singleLine = true,
                isError = translationIsEmptyError,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.boundedClickable {
                            translate = ""
                        }
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = sizeM)
            )

            DefaultSpacer(space = sizeXS)

            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text(text = stringResource(id = R.string.label_enter_category)) },
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.boundedClickable {
                            category = ""
                        }
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = sizeM)
            )

            DefaultSpacer()

            OutlinedTextField(
                value = example,
                onValueChange = { example = it },
                label = { Text(text = stringResource(id = R.string.label_enter_example)) },
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.boundedClickable {
                            example = ""
                        }
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = sizeM)
            )
        }

        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(id = R.string.label_action_save)) },
            icon = { Icon(imageVector = Icons.Rounded.Done, contentDescription = null) },
            onClick = {
                wordIsEmptyError = word.isBlank()
                translationIsEmptyError = translate.isBlank()

                if (word.isNotBlank() && translate.isNotBlank()) {
                    cardsViewModel.saveCard(
                        word = word,
                        translation = translate,
                        category = category,
                        example = example,
                    )
                    navController.navigateUp()
                }
            },
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(spaceM)
        )
    }
}

@Preview
@Composable
fun NewCardScreenPreview() {
    NewCardScreen(rememberNavController())
}
