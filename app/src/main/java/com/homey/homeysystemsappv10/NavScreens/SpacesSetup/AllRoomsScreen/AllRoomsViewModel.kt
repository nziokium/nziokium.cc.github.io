package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.AllRoomsScreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class RoomToBuilding(
    var buildingName: String,
    var roomName: String
)

class AllRoomsViewModel: ViewModel() {

    private val allRoomsRepository = AllRoomsRepository()

    private val _roomList = mutableStateListOf<RoomToBuilding>()
    val roomList: List<RoomToBuilding> get() = _roomList

    fun fetchAllRooms() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val roomsMap = allRoomsRepository.fetchAllRooms()

                // Clear existing room list before adding new data
                _roomList.clear()

                // Flatten roomsMap to list of RoomToBuilding objects
                roomsMap.forEach { (buildingName, rooms) ->
                    rooms.forEach { room ->
                        _roomList.add(RoomToBuilding(buildingName, room.name)) // Here's the correction
                    }
                }

                Log.d("Success", "All rooms fetched")

            } catch (e: Exception) {
                // Handle exception (e.g., display error message)
                Log.e("Error", "Failed to fetch rooms", e)
            }
        }
    }


}


