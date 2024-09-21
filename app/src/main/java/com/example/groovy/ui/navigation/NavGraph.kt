package com.example.groovy.ui.navigation


import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.groovy.other.MusicControllerUiState
import com.example.groovy.ui.home.HomeEvent
import com.example.groovy.ui.home.HomeScreen
import com.example.groovy.ui.home.HomeViewModel
import com.example.groovy.ui.home.component.HomeBottomBar
import com.example.groovy.ui.search.SearchScreen
import com.example.groovy.ui.settings.SettingsScreen
import com.example.groovy.ui.songs_screen.SongEvent
import com.example.groovy.ui.songs_screen.SongScreen
import com.example.groovy.ui.songs_screen.SongViewModel
import com.example.groovy.ui.viewmodels.SharedViewModel
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MusicPlayerApp(sharedViewModel: SharedViewModel) {
    val navController = rememberNavController()
    val (isBottomBarVisible, setBottomBarVisible) = remember { mutableStateOf(true) }


    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(navBackStackEntry) {
        val route = navBackStackEntry?.destination?.route
        setBottomBarVisible(route != Destination.songScreen)
    }

    Scaffold(
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            MusicPlayerNavHost(
                navController = navController,
                sharedViewModel = sharedViewModel,
                setBottomBarVisible = setBottomBarVisible,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicPlayerNavHost(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    setBottomBarVisible: (Boolean) -> Unit,
    modifier: Modifier
) {
    val musicControllerUiState = sharedViewModel.musicControllerUiState
    val activity = (LocalContext.current as ComponentActivity)

    NavHost(navController = navController, startDestination = Destination.home) {
        composable(route = Destination.home) {
            val mainViewModel: HomeViewModel = hiltViewModel()
            Box(modifier = Modifier.fillMaxSize()) {
                SettingsScreen(
                    onEvent = mainViewModel::onEvent,
                    uiState = mainViewModel.homeUiState,
                )

                HomeBottomBar(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 56.dp),
                    onEvent = mainViewModel::onEvent,
                    song = musicControllerUiState.currentSong,
                    playerState = musicControllerUiState.playerState,
                    onBarClick = {
                        navController.navigate(Destination.songScreen)
                        setBottomBarVisible(false)
                    },
                    songProgress = musicControllerUiState,
                )
            }
        }

        composable(route = Destination.search) {
            Box(modifier = Modifier.fillMaxSize()) {
                val mainViewModel: HomeViewModel = hiltViewModel()
                SearchScreen(
                    onEvent = mainViewModel::onEvent,
                    uiState = mainViewModel.homeUiState,
                )

                HomeBottomBar(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 56.dp),
                    onEvent = mainViewModel::onEvent,
                    song = musicControllerUiState.currentSong,
                    playerState = musicControllerUiState.playerState,
                    onBarClick = {
                        navController.navigate(Destination.songScreen)
                        setBottomBarVisible(false)
                    },
                    songProgress = musicControllerUiState,
                )
            }
        }

        composable(route = Destination.settings) {
            val mainViewModel: HomeViewModel = hiltViewModel()
            Box(modifier = Modifier.fillMaxSize()) {
                SettingsScreen(
                onEvent = mainViewModel::onEvent,
                uiState = mainViewModel.homeUiState,
                )

                HomeBottomBar(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 56.dp),
                    onEvent = mainViewModel::onEvent,
                    song = musicControllerUiState.currentSong,
                    playerState = musicControllerUiState.playerState,
                    onBarClick = {
                        navController.navigate(Destination.songScreen)
                        setBottomBarVisible(false)
                    },
                    songProgress = musicControllerUiState,
                )
            }
        }

        composable(route = Destination.songScreen) {
            val songViewModel: SongViewModel = hiltViewModel()
            SongScreen(
                onEvent = songViewModel::onEvent,
                musicControllerUiState = musicControllerUiState,
                onNavigateUp = {
                    navController.navigateUp()
                    setBottomBarVisible(true)
                },
                modifier = Modifier.fillMaxSize()
            )
            BackHandler {
                navController.navigateUp()
                setBottomBarVisible(true)
            }
        }
        composable(route = Destination.settings) {
            val mainViewModel: HomeViewModel = hiltViewModel()
            val isInitialized = rememberSaveable { mutableStateOf(false) }

            if (!isInitialized.value) {
                LaunchedEffect(key1 = Unit) {
                    mainViewModel.onEvent(HomeEvent.FetchSong)
                    isInitialized.value = true
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                HomeScreen(
                    onEvent = mainViewModel::onEvent,
                    uiState = mainViewModel.homeUiState,
                )

                HomeBottomBar(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 56.dp),
                    onEvent = mainViewModel::onEvent,
                    song = musicControllerUiState.currentSong,
                    playerState = musicControllerUiState.playerState,
                    onBarClick = {
                        navController.navigate(Destination.songScreen)
                        setBottomBarVisible(false)
                    },
                    songProgress = musicControllerUiState,
                )
            }
        }
    }
}
