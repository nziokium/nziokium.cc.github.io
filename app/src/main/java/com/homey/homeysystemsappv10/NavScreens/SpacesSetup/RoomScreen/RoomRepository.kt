package com.homey.viewmodeltester.SpacesSetup.RoomScreen

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

data class WriteToRoom(
    var name: String
)

data class ReadFromRoom(
    var name: String = ""
)

class RoomRepository{
    val db = Firebase.firestore

    suspend fun saveRoomToBuilding(buildingName: String, room: WriteToRoom) {

        try {
            db.collection("Buildings")
                .document(buildingName)
                .collection("Rooms")
                .document(room.name) // Use the room name as the document ID
                .set(room)
                .await()
        } catch (e: Exception) {
            Log.d("Error","Room Not Saved")
        }
    }

    suspend fun fetchRoomsFromBuilding(buildingName: String): List<ReadFromRoom> {
        return try {
            val querySnapshot = db.collection("Buildings")
                .document(buildingName)
                .collection("Rooms")
                .get()
                .await()

            val rooms = mutableListOf<ReadFromRoom>()
            for (document in querySnapshot.documents) {
                val room = document.toObject(ReadFromRoom::class.java)
                room?.let {
                    rooms.add(it)
                }
            }
            Log.d("Success", "Rooms Fetched from Building")
            rooms
        } catch (e: Exception) {
            // Handle exceptions
            Log.d("Message", "No Rooms")
            emptyList()

        }
    }

}