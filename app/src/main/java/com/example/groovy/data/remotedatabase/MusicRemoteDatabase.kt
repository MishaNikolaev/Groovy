package com.example.groovy.data.remotedatabase

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot

class MusicRemoteDatabase(private val songCollection: CollectionReference) {

    fun getAllSongs(): Task<QuerySnapshot> = songCollection.get()
        .addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                Log.d("MusicRemoteDatabase", "Document: ${document.data}")
            }
        }
        .addOnFailureListener { exception ->
            Log.w("MusicRemoteDatabase", "Error getting songs: ", exception)
        }
}