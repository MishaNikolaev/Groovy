package com.example.groovy.domain.repository

import com.example.groovy.domain.model.Song
import com.example.groovy.other.Resource
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getSongs(): Flow<Resource<List<Song>>>
}