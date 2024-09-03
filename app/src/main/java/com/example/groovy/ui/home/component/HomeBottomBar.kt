package com.example.groovy.ui.home.component


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.groovy.R
import com.example.groovy.domain.model.Song
import com.example.groovy.other.MusicControllerUiState
import com.example.groovy.other.PlayerState
import com.example.groovy.ui.home.HomeEvent
import kotlinx.coroutines.delay
@Composable
fun HomeBottomBar(
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
    playerState: PlayerState?,
    song: Song?,
    songProgress: MusicControllerUiState?,
    onBarClick: () -> Unit,

) {
    var offsetX by remember { mutableStateOf(0f) }
    var currentProgress by remember { mutableStateOf(0f) }

    AnimatedVisibility(
        visible = playerState != PlayerState.STOPPED,
        modifier = modifier
    ) {
        if (song != null && songProgress != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                when {
                                    offsetX > 0 -> onEvent(HomeEvent.SkipToPreviousSong)
                                    offsetX < 0 -> onEvent(HomeEvent.SkipToNextSong)
                                }
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                val (x, _) = dragAmount
                                offsetX = x
                            }
                        )
                    }
                    .background(if (!isSystemInDarkTheme()) Color.LightGray else Color.DarkGray)
            ) {
                Column {
                    // Прогресс бар
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = currentProgress.coerceIn(0f, 1f))
                            .height(2.dp)
                            .background(Color(0xFFFFC107))
                    )

                    HomeBottomBarItem(
                        song = song,
                        onEvent = onEvent,
                        playerState = playerState,
                        onBarClick = onBarClick
                    )
                }
            }

            LaunchedEffect(playerState, songProgress.currentPosition) {
                while (playerState == PlayerState.PLAYING) {
                    currentProgress = if (songProgress.totalDuration > 0) {
                        songProgress.currentPosition.toFloat() / songProgress.totalDuration.toFloat()
                    } else {
                        0f
                    }
                    delay(1000L)
                }
            }
        }
    }
}

@Composable
fun HomeBottomBarItem(
    song: Song,
    onEvent: (HomeEvent) -> Unit,
    playerState: PlayerState?,
    onBarClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(Color.White)
            .clickable(onClick = { onBarClick() })
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(song.imageUrl),
                contentDescription = song.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .offset(16.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(vertical = 8.dp, horizontal = 32.dp),
            ) {
                Text(
                    song.title,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    song.subtitle,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .graphicsLayer { alpha = 0.60f }
                )
            }
            val painter = rememberAsyncImagePainter(
                if (playerState == PlayerState.PLAYING) R.drawable.pause else R.drawable.play_button_arrowhead
            )

            Image(
                painter = painter,
                contentDescription = "Music",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(25.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false, radius = 24.dp)
                    ) {
                        if (playerState == PlayerState.PLAYING) {
                            onEvent(HomeEvent.PauseSong)
                        } else {
                            onEvent(HomeEvent.ResumeSong)
                        }
                    }
            )
        }
    }
}