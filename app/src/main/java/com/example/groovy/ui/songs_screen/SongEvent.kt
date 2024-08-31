package com.example.groovy.ui.songs_screen

sealed class SongEvent {
    object PauseSong : SongEvent()
    object ResumeSong : SongEvent()
    object SkipToNextSong : SongEvent()
    object SkipToPreviousSong : SongEvent()
    data class SeekSongToPosition(val position: Long) : SongEvent()
}