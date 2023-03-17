package com.shamilov.core.android.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun DefaultSpacer(space: Dp) {
    Spacer(modifier = Modifier.size(space))
}
