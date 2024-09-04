package com.example.groovy.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {
    object Home : BottomNavItem("", Icons.Default.Home, Destination.home)
    object Search : BottomNavItem("", Icons.Default.Search, Destination.search)
    object Settings : BottomNavItem("", Icons.Default.Settings, Destination.settings)
}