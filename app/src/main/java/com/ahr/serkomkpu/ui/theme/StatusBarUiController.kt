package com.ahr.serkomkpu.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun StatusBarUiController(
    useDarkIcons: Boolean = !isSystemInDarkTheme(),
    color: Color = MaterialTheme.colorScheme.background
) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = useDarkIcons
        )
        onDispose {}
    }
}