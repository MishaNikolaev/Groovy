package com.example.groovy.data.repository

import com.example.groovy.data.dto.SongDto
import com.example.groovy.data.mapper.toSong
import com.example.groovy.data.remotedatabase.MusicRemoteDatabase
import com.example.groovy.domain.repository.MusicRepository
import com.example.groovy.other.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.flow


class MusicRepositoryImpl @Inject constructor(
    private val musicRemoteDatabase: MusicRemoteDatabase,
) :
    MusicRepository {
    override fun getSongs() =
        flow {
            val songs = musicRemoteDatabase.getAllSongs().await().toObjects<SongDto>()

            if (songs.isNotEmpty()) {
                emit(Resource.Success(songs.map { it.toSong() }))
            }

        }

}