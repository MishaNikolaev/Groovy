package com.example.groovy.ui.settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.groovy.ui.home.HomeEvent
import com.example.groovy.ui.home.HomeUiState

@Composable
fun SettingsScreen(onEvent: (HomeEvent) -> Unit,
                   uiState: HomeUiState,){
    Text(text = "Settings")
}