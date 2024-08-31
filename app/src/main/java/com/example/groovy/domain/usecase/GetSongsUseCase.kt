package com.example.groovy.domain.usecase

import com.example.groovy.domain.repository.MusicRepository
import javax.inject.Inject

class GetSongsUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    operator fun invoke() = musicRepository.getSongs()
}