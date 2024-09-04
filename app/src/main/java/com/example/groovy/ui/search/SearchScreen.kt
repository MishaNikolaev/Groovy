package com.example.groovy.ui.search

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.groovy.ui.home.HomeEvent
import com.example.groovy.ui.home.HomeUiState

@Composable
fun SearchScreen(onEvent: (HomeEvent) -> Unit,
                 uiState: HomeUiState,){
    Text(text = "Search")
}