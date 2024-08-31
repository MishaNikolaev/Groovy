package com.example.groovy.domain.usecase

import com.example.groovy.domain.service.MusicController
import javax.inject.Inject

class PlaySongUseCase @Inject constructor(private  val musicController: MusicController) {
    operator fun invoke(mediaItemIndex: Int) {
        musicController.play(mediaItemIndex)
    }
}