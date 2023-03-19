package com.shamilov.core.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.shamilov.core.android.ui.main.MainScreen
import com.shamilov.core.android.ui.theme.WordTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordTheme {
                MainScreen()
            }
        }
    }
}