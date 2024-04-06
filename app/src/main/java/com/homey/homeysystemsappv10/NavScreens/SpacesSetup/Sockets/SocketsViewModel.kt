package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.Sockets

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class SocketsViewModel : ViewModel() {
    var bool by mutableStateOf(true)
    var name by mutableStateOf("on")
    var sumValue by mutableStateOf(0)

    private val socketsRepository = SocketsRepository()

    init {
        // Collect the shared flow for socket status from the repository
        viewModelScope.launch {
            socketsRepository.getSocketStatus().collect { switchState ->
                // Update the bool and name properties based on the switch state
                bool = switchState
                name = if (switchState) "on" else "off"
            }
        }

        // Collect the shared flow for sum value from the repository
        viewModelScope.launch {
            socketsRepository.getSumValue().collect { sum ->
                // Update the sumValue property
                sumValue = sum
            }
        }
    }

    // Function to update switch state
    fun updateSwitchState(newState: Boolean) {
        viewModelScope.launch {
            socketsRepository.updateSwitchState(newState)
        }
    }
}
