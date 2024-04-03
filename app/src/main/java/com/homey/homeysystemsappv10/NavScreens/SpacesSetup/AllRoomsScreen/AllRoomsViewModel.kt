package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.AllRoomsScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllRoomsViewModel: ViewModel() {

    val allRoomsRepository = AllRoomsRepository()

    private val _roomList = mutableStateListOf<String>()
    val roomList: List<String> get() = _roomList
    fun fetchAllRooms() {
        viewModelScope.launch {
            val rooms = allRoomsRepository.fetchAllRooms()
            _roomList.clear()
            _roomList.addAll(rooms.map { it.name })
        }
    }

    init {
        fetchAllRooms()
    }

}