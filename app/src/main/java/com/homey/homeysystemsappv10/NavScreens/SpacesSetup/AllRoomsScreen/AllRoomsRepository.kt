package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.AllRoomsScreen

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.homey.viewmodeltester.SpacesSetup.RoomScreen.ReadFromRoom
import kotlinx.coroutines.tasks.await



class AllRoomsRepository {

    val db = Firebase.firestore
    suspend fun fetchAllRooms(): Map<String, List<ReadFromRoom>> {
        return try {
            val buildingQuerySnapshot = db.collection("Buildings").get().await()

            val roomsMap = mutableMapOf<String, List<ReadFromRoom>>()

            for (buildingDocument in buildingQuerySnapshot.documents) {
                val buildingName = buildingDocument.id
                val roomsQuerySnapshot = db.collection("Rooms")
                    .whereEqualTo("buildingName", buildingName)
                    .get()
                    .await()

                val rooms = mutableListOf<ReadFromRoom>()
                for (roomDocument in roomsQuerySnapshot.documents) {
                    val room = roomDocument.toObject(ReadFromRoom::class.java)
                    room?.let {
                        rooms.add(it)
                    }
                }

                roomsMap[buildingName] = rooms
            }

            Log.d("Success", "All rooms fetched from firebase")
            roomsMap
        } catch (e: Exception) {
            // Handle exceptions
            Log.e("Error", "Failed to fetch rooms", e)
            emptyMap()
        }
    }



}
