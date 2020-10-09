package com.toturials.foodviet.preferences

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel(application: Application):AndroidViewModel(application) {
    val repository = PreferencesDataStore(application)
    val readNumberOfOrder = repository.readNumberOfOrder.asLiveData()
    fun savedNumberOfOrder(number:Int)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.savedNumberOfOrder(number)
        }
    }
}