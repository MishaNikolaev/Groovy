package com.example.groovy.domain.usecase

import com.example.groovy.domain.service.MusicController
import javax.inject.Inject

class GetCurrentSongPositionUseCase @Inject constructor(
    private val musicController: MusicController
) {
    operator fun invoke() = musicController.getCurrentPosition()
}