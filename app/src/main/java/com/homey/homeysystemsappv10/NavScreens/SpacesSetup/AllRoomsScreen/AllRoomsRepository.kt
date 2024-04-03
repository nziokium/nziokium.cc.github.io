package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.AllRoomsScreen

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.homey.viewmodeltester.SpacesSetup.RoomScreen.ReadFromRoom
import kotlinx.coroutines.tasks.await

class AllRoomsRepository {

    val db = Firebase.firestore
    suspend fun fetchAllRooms(): List<ReadFromRoom> {
        return try {
            val buildingQuerySnapshot = db.collection("Buildings")
                .get()
                .await()

            val rooms = mutableListOf<ReadFromRoom>()

            for (buildingDocument in buildingQuerySnapshot.documents) {
                val buildingName = buildingDocument.id
                val roomsQuerySnapshot = db.collection("Buildings")
                    .document(buildingName)
                    .collection("Rooms")
                    .get()
                    .await()

                for (roomDocument in roomsQuerySnapshot.documents) {
                    val room = roomDocument.toObject(ReadFromRoom::class.java)
                    room?.let {
                        rooms.add(it)
                    }
                }
            }

            Log.d("Success", "All rooms fetched")
            rooms
        } catch (e: Exception) {
            // Handle exceptions
            Log.e("Error", "Failed to fetch rooms", e)
            emptyList()
        }
    }


}
