package com.example.logiqlinkapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logiqlinkapplication.room.InventoryDataEntity
import com.example.logiqlinkapplication.room.InventoryRepository
import kotlinx.coroutines.launch

/**
 * Created by Ritik on 2/1/2024.
 */
class InventoryViewModel(private  val repository: InventoryRepository):ViewModel() {

    private val _inventoryData = repository.inventoryData
    val inventoryData: LiveData<List<InventoryDataEntity>> get() = _inventoryData


    fun onGetInventory(){
        viewModelScope.launch {
            repository.fetchDataAndSave()
        }

    }

    fun getAllInventory(){
        viewModelScope.launch {
            repository.getInventory()
        }
    }

    fun insertUserData(inventoryDataEntity: InventoryDataEntity) {
        viewModelScope.launch {
            repository.insertInventoryData(inventoryDataEntity)
        }
    }

    fun onDeleteUser(inventoryData: InventoryDataEntity){
        viewModelScope.launch {
            repository.deleteInventory(inventoryData)
        }

    }
}