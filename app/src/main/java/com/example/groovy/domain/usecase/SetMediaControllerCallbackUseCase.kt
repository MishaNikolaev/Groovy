package com.example.groovy.domain.usecase

import com.example.groovy.domain.model.Song
import com.example.groovy.domain.service.MusicController
import com.example.groovy.other.PlayerState
import javax.inject.Inject

class SetMediaControllerCallbackUseCase @Inject constructor(
    private val musicController: MusicController
) {
    operator fun invoke(
        callback: (
            playerState: PlayerState,
            currentSong: Song?,
            currentPosition: Long,
            totalDuration: Long,
            isShuffleEnabled: Boolean,
            isRepeatOneEnabled: Boolean
        ) -> Unit
    ) {
        musicController.mediaControllerCallback = callback
    }
}