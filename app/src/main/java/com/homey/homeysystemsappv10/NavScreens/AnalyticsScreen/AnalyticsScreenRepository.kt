package com.homey.homeysystemsappv10.NavScreens.AnalyticsScreen

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun getDataFromFirestoreRealtime(): Socket {
    val db = FirebaseFirestore.getInstance()
    var socket = Socket()

    try {
        val documentSnapshot = db.collection("Buildings")
            .document("Socket1")
            .get()
            .await()

        if (documentSnapshot.exists()) {
            socket = documentSnapshot.toObject(Socket::class.java) ?: Socket()
        } else {
            // Handle the case where the document does not exist
        }
    } catch (e: Exception) {
        // Handle exceptions, log, or throw them if necessary
    }

    return socket
}