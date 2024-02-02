package com.example.logiqlinkapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Ritik on 2/1/2024.
 */
@Dao
interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllInventory(inventoryDataList: List<InventoryDataEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventory(inventoryData: InventoryDataEntity)


    @Query("SELECT * FROM inventoryData")
    suspend fun getAllInventory(): List<InventoryDataEntity>


    @Delete
    suspend fun delete(inventoryData: InventoryDataEntity)
}