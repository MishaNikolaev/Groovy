package com.example.groovy.di

import android.content.Context
import com.example.groovy.data.remotedatabase.MusicRemoteDatabase
import com.example.groovy.data.repository.MusicRepositoryImpl
import com.example.groovy.data.service.MusicControllerImpl
import com.example.groovy.domain.repository.MusicRepository
import com.example.groovy.domain.service.MusicController
import com.example.groovy.other.Constants
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCollection() = FirebaseFirestore.getInstance().collection(Constants.SONG_COLLECTION)

    @Singleton
    @Provides
    fun provideMusicDatabase(songCollection: CollectionReference) =
        MusicRemoteDatabase(songCollection)


    @Singleton
    @Provides
    fun provideMusicRepository(
        musicRemoteDatabase: MusicRemoteDatabase,
    ): MusicRepository =
        MusicRepositoryImpl(musicRemoteDatabase)

    @Singleton
    @Provides
    fun provideMusicController(@ApplicationContext context: Context): MusicController =
        MusicControllerImpl(context)
}