package com.shamilov.core.android.ui.new_card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shamilov.core.android.ui.components.Toolbar
import com.shamilov.core.android.ui.theme.sizeM
import com.shamilov.core.android.ui.theme.sizeXS
import com.shamilov.core.android.ui.utils.DefaultSpacer

@Composable
fun NewCardScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {

        var word by remember { mutableStateOf("") }
        var translate by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }
        var example by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Toolbar("New card") { navController.navigateUp() }
            OutlinedTextField(
                value = word,
                onValueChange = { word = it },
                label = { Text(text = "Word *") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = { word = "" }
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = sizeM)
            )

            DefaultSpacer(space = sizeXS)

            OutlinedTextField(
                value = translate,
                onValueChange = { translate = it },
                label = { Text(text = "Translate *") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = { translate = "" }
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = sizeM)
            )

            DefaultSpacer(space = sizeXS)

            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text(text = "Category") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = { category = "" }
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = sizeM)
            )

            DefaultSpacer()

            OutlinedTextField(
                value = example,
                onValueChange = { example = it },
                label = { Text(text = "Example") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = { example = "" }
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = sizeM)
            )
        }

        Button(
            onClick = { /*TODO*/ },
            enabled = word.isNotBlank() && translate.isNotBlank(),
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(sizeM),
            colors = ButtonDefaults.filledTonalButtonColors()
        ) {
            Text(text = "Save card")
        }
        DefaultSpacer()
    }
}

@Preview
@Composable
fun NewCardScreenPreview() {
    NewCardScreen(rememberNavController())
}