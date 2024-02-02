package com.example.logiqlinkapplication.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.logiqlinkapplication.apiService.ApiService

/**
 * Created by Ritik on 2/1/2024.
 */
class InventoryRepository(
    private val inventoryDao: InventoryDao,
    private val apiService: ApiService
) {
    private val _inventoryData = MutableLiveData<List<InventoryDataEntity>>()
    val inventoryData: LiveData<List<InventoryDataEntity>> get() = _inventoryData

    suspend fun fetchDataAndSave() {
        try {
            val response = apiService.getInventory()
            if (response.isSuccessful) {
                val inventoryDataList = response.body()?.map { inventoryData ->
                    InventoryDataEntity(
                        id = inventoryData.id,
                        type = inventoryData.type,
                        color = inventoryData.color,
                        available = inventoryData.available,
                        title = inventoryData.title,
                        description = inventoryData.description,
                        price = inventoryData.price,
                    )

                } ?: emptyList()
                _inventoryData.postValue(inventoryDataList)
                saveDataToDatabase(inventoryDataList)

            }

        } catch (e: Exception) {
        }
    }

    suspend fun getInventory(): List<InventoryDataEntity> {
        return inventoryDao.getAllInventory()
    }

    suspend fun deleteInventory(inventoryData: InventoryDataEntity) {
        inventoryDao.delete(inventoryData)
    }

    private suspend fun saveDataToDatabase(inventoryDataList: List<InventoryDataEntity>) {
        inventoryDao.insertAllInventory(inventoryDataList)
    }

    suspend fun insertInventoryData(inventoryDataEntity: InventoryDataEntity) {
        inventoryDao.insertInventory(inventoryDataEntity)
    }


}