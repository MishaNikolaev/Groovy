package com.example.groovy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.groovy.data.service.MusicService
import com.example.groovy.ui.navigation.MusicPlayerApp
import com.example.groovy.ui.theme.MusicPlayerTheme
import com.example.groovy.ui.viewmodels.SharedViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {

            val systemUiController = rememberSystemUiController()

            val useDarkIcons = !isSystemInDarkTheme()

            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons
                )
            }
            MusicPlayerTheme {
                MusicPlayerApp(
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        sharedViewModel.destroyMediaController()
        stopService(Intent(this, MusicService::class.java))
    }
}
