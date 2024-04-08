package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.IndividualRooms

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class AddSocket(
    var socketName: String = ""
)

data class GetSocket(
    var name: String = ""
)


class IndividualRoomsRepository(
    private var buildingName: String = "",
    private var roomName: String = ""
) {
    private val db = Firebase.firestore


    private var roomRef = db.collection("Buildings")
                .document(buildingName)
                .collection("Rooms")
                .document(roomName)
                .collection("Sockets")


    fun saveSocketToRoom(socket: AddSocket){
        roomRef.document(socket.socketName)
            .set(socket)
            .addOnSuccessListener { Log.d(TAG,"Document Saved Successfully") }
            .addOnFailureListener{Log.d(TAG, "Error Saving Document")}

    }

    suspend fun getSocketsFromRooms(): List<GetSocket> {
        return try {
            val querySnapshot = roomRef
                .get()
                .await()

            val sockets = mutableListOf<GetSocket>()
            for (document in querySnapshot.documents) {
                val room = document.toObject(GetSocket::class.java)
                room?.let {
                    sockets.add(it)
                }
            }
            Log.d("Success", "Sockets Fetched")
            Log.d("Message", "This is $buildingName within $roomName")

            sockets
        } catch (e: Exception) {
            // Handle exceptions
            Log.d("Message", "No Sockets")
            emptyList()

        }
    }
}