package com.shamilov.core.android.widget.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.shamilov.core.android.ui.theme.DarkColors
import com.shamilov.core.android.ui.theme.LightColors

/**
 * @author Shamilov on 31.05.2023
 */
@Composable
fun WidgetTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
    )
}
