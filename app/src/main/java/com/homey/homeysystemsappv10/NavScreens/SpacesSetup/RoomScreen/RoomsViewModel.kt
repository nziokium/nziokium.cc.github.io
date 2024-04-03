package com.homey.viewmodeltester.SpacesSetup.RoomScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomsViewModel(var buildingName: String = ""): ViewModel() {
    var roomName by mutableStateOf("")


    private val _roomList = mutableStateListOf<String>()
    val roomList: List<String> get() = _roomList



    val roomRepository = RoomRepository()

    fun addRoom() {
        isDialogShown = false
        viewModelScope.launch {
            saveRoomToBuilding()
            fetchRooms()
            Log.d("Message",
                "Data Sent to Add rooms, with building Name = $buildingName")
        }
    }


    //Create the logic for showing and hiding the dialog box

    //Dialog should be hidden by default
    var isDialogShown by mutableStateOf(false)
        private set

    fun onAddClick(){
        isDialogShown = true
    }

    fun onCancelClick(){
        isDialogShown = false
    }

    //Create a function for saving data to the database
    fun saveRoomToBuilding() {

        val _room = WriteToRoom(

            name = roomName
        )

        // Launch a coroutine in the IO dispatcher
        viewModelScope.launch(Dispatchers.IO) {
            try {
                roomRepository.saveRoomToBuilding(buildingName, _room)
                withContext(Dispatchers.Main) {
                    // Add Dialog for successful save
                    Log.d("Message" , "${_room.name}")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Handle error with Dialog
                }
            }
        }
    }



    fun fetchRooms() {
        viewModelScope.launch {
            val rooms = roomRepository.fetchRoomsFromBuilding(buildingName)
            _roomList.clear()
            _roomList.addAll(rooms.map { it.name })

        }


    }

    init {
        fetchRooms()
    }
}