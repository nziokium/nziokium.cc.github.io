package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.Sockets

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.callbackFlow


class SocketsRepository {
    private val db = FirebaseFirestore.getInstance()

    private val socketDocRef = db.collection("Buildings")
        .document("Building 1")
        .collection("Rooms")
        .document("Room 1")
        .collection("Sockets")
        .document("Socket 1")

    fun updateSwitchState(newState: Boolean) {
        socketDocRef.update("switchState", newState)
            .addOnSuccessListener {
                // Update successful
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }
    fun getSocketStatus(): Flow<Boolean> {
        val _socketStatusFlow = MutableStateFlow<Boolean>(false)
        val socketStatusFlow = _socketStatusFlow.asSharedFlow()

        val listener = socketDocRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                // Handle error
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val switchState = snapshot.getBoolean("switchState") ?: false
                _socketStatusFlow.value = switchState
            }
        }

        return socketStatusFlow
    }

    // Function to calculate the sum of numerical values in Socket Data and emit it as a flow
    fun getSumValue(): Flow<Int> {
        val sumFlow = MutableStateFlow(0)
        val dataDocRef = socketDocRef.collection("Socket Data")
        dataDocRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                // Handle error
                return@addSnapshotListener
            }

            try {
                if (snapshot != null) {
                    var sum = 0
                    for (doc in snapshot.documents) {
                        val value = doc.getString("value") ?: "0"
                        sum += value.toInt()
                    }
                    sumFlow.value = sum
                }
            }catch (e: Exception){
                Log.d("Message", "There is an error")
            }

        }

        return sumFlow
    }
}
