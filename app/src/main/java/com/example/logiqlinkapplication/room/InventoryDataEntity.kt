package com.example.logiqlinkapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ritik on 2/2/2024.
 */
@Entity(tableName = "inventoryData")
data class InventoryDataEntity(
    @PrimaryKey
    val id: String,
    val type: String?,
    val color: String?,
    val available: Int?,
    val title: String?,
    val description: String?,
    val price: String?
)