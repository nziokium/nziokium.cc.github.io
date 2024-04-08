package com.homey.homeysystemsappv10.NavScreens.BuildingScreen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuildingsViewModel : ViewModel() {


    var buildingName by mutableStateOf("")

    private val _buildingArray = mutableStateListOf<String>()
    val buildingArray: List<String> get() = _buildingArray


    val buildingRepository = BuildingRepository()

    fun addBuilding() {
        isDialogShown = false
        viewModelScope.launch(Dispatchers.IO) {
            saveBuildingToFirestore()
            fetchBuildings()
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
    fun saveBuildingToFirestore() {

        val _building = WriteToBuilding(
            name = buildingName
        )

        // Launch a coroutine in the IO dispatcher
        viewModelScope.launch(Dispatchers.IO) {
            try {
                buildingRepository.saveBuildingToFirebase(_building)
                withContext(Dispatchers.Main) {
                    // Add Dialog for successful save
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Handle error with Dialog
                }
            }
        }
    }



    fun fetchBuildings() {
        viewModelScope.launch {
            val buildings = buildingRepository.fetchBuildings()
            _buildingArray.clear()
            _buildingArray.addAll(buildings.map { it.name })
        }
    }

    init {
        fetchBuildings()
    }





}
