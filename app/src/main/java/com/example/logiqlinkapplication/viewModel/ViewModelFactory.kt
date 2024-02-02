package com.example.logiqlinkapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.logiqlinkapplication.room.InventoryRepository

/**
 * Created by Ritik on 2/1/2024.
 */
class ViewModelFactory (private val inventoryRepository: InventoryRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)){

            return InventoryViewModel(inventoryRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}