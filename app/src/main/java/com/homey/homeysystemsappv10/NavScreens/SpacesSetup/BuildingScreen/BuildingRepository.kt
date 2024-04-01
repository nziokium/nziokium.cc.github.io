package com.homey.homeysystemsappv10.NavScreens.BuildingScreen

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


data class WriteToBuilding(
    var name: String
)

data class ReadFromBuilding(
    var name: String = ""
)

class BuildingRepository {
    val db = Firebase.firestore

    suspend fun saveBuildingToFirebase(building: WriteToBuilding) {


        try {
            db.collection("Buildings")
                .document(building.name) // Use the name as the document ID
                .set(building) // Set the fields of the document
                .await()

            // After saving the building, update the list of buildings
            fetchBuildings()

        } catch (e: Exception) {
            // Handle exceptions
        }
    }

    suspend fun fetchBuildings(): List<ReadFromBuilding> {

        return try {
            val querySnapshot = db.collection("Buildings").get().await()
            val buildings = mutableListOf<ReadFromBuilding>()

            for (document in querySnapshot.documents) {
                val building = document.toObject(ReadFromBuilding::class.java)
                building?.let {
                    buildings.add(it)
                }
            }

            buildings
        } catch (e: Exception) {
            // Handle exceptions
            Log.d("Error", "Return unsuccessful: ${e.message}")
            emptyList() // Return empty list if there's an error
        }
    }

}
