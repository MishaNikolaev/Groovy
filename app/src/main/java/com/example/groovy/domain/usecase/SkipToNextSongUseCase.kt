package com.example.groovy.domain.usecase

import com.example.groovy.domain.model.Song
import com.example.groovy.domain.service.MusicController
import javax.inject.Inject

class SkipToNextSongUseCase @Inject constructor(private val musicController: MusicController) {
    operator fun invoke(updateHomeUi: (Song?) -> Unit) {
        musicController.skipToNextSong()
        updateHomeUi(musicController.getCurrentSong())
    }
}