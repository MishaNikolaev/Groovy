package com.example.groovy.ui.home

import com.example.groovy.domain.model.Song

sealed class HomeEvent {
    object PlaySong : HomeEvent()
    object PauseSong : HomeEvent()
    object ResumeSong : HomeEvent()
    object FetchSong : HomeEvent()
    object SkipToNextSong : HomeEvent()
    object SkipToPreviousSong : HomeEvent()
    data class OnSongSelected(val selectedSong: Song) : HomeEvent()
    data class SeekSongToPosition(val newPosition: Long) : HomeEvent()
}