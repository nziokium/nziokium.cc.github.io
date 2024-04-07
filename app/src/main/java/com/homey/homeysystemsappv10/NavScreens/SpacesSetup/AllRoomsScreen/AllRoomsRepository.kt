package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.AllRoomsScreen

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class GetSocket(
    var name: String = ""
)

class AllRoomsRepository {

    val db = Firebase.firestore
    suspend fun fetchAllRooms(): Map<String, List<GetSocket>> {
        return try {
            val buildingQuerySnapshot = db.collection("Buildings").get().await()

            val roomsMap = mutableMapOf<String, List<GetSocket>>()

            for (buildingDocument in buildingQuerySnapshot.documents) {
                val buildingName = buildingDocument.id
                val roomsQuerySnapshot = db.collection("Rooms")
                    .whereEqualTo("buildingName", buildingName)
                    .get()
                    .await()

                val rooms = mutableListOf<GetSocket>()
                for (roomDocument in roomsQuerySnapshot.documents) {
                    val room = roomDocument.toObject(GetSocket::class.java)
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
