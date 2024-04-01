package com.homey.homeysystemsappv10.NavScreens.AnalyticsScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AnalyticsScreenViewModel: ViewModel() {

    var isDataLoaded by(mutableStateOf(false))

    val number = mutableStateOf(Socket())

    init {
        getData()
    }


    fun getData() {
        viewModelScope.launch {
            // Load data from Firestore
            number.value= getDataFromFirestoreRealtime()

            // Set isDataLoaded to true after data is loaded
            isDataLoaded = true
        }
    }
}