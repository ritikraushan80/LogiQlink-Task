package com.example.logiqlinkapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Ritik on 2/1/2024.
 */
@Database(entities = [InventoryDataEntity::class], version = 1)
abstract class InventoryDatabase :RoomDatabase() {
    abstract fun  inventoryDao() : InventoryDao

    companion object{
        @Volatile
        private var INSTANCE: InventoryDatabase? = null

        fun getUserDatabase(context: Context): InventoryDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, InventoryDatabase::class.java,
                    "inventory_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}