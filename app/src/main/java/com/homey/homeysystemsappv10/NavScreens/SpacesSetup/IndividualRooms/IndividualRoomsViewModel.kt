package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.IndividualRooms

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homey.viewmodeltester.SpacesSetup.RoomScreen.RoomsViewModel
import kotlinx.coroutines.launch

class IndividualRoomsViewModel(
    private var buildingName: String = "",
    private var roomName: String = ""): ViewModel() {

    fun debug(){
        Log.d(TAG, "This is $roomName within $buildingName ")

    }

    var _roomName = roomName
    var _buildingName = buildingName

    private val roomsViewModel = RoomsViewModel()

    private val _socketList = mutableStateListOf<String>()
    val socketList: List<String> get() = _socketList

    val individualRoomsRepository = IndividualRoomsRepository(
        buildingName = buildingName,
        roomName = roomName
    )
    fun fetchSockets() {
        viewModelScope.launch {
            val rooms = individualRoomsRepository.getSocketsFromRooms()
            _socketList.clear()
            _socketList.addAll(rooms.map { it.name })
        }
    }

    init {
        fetchSockets()
        debug()
    }
}