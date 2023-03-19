package com.shamilov.core.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.shamilov.core.android.ui.theme.sizeXS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String? = null,
    navigationBackIcon: ImageVector = Icons.Rounded.Close,
    onClick: () -> Unit,
) {
    TopAppBar(
        title = { title?.let { Text(text = it) } },
        navigationIcon = {
            Icon(
                imageVector = navigationBackIcon,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = sizeXS)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = { onClick() }
                    )
            )
        }
    )
}

@Preview
@Composable
fun ToolbarPreview() {
    Toolbar("Title") {

    }
}