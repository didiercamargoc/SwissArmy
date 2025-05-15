package com.coding.swissarmy.common.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coding.swissarmy.common.database.entities.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class SwissArmyDatabase : RoomDatabase() {

    abstract fun swissArmyDAO(): SwissArmyDAO

    companion object {
        @Volatile
        private var INSTANCE: SwissArmyDatabase? = null

        fun getDatabase(context: Context): SwissArmyDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SwissArmyDatabase::class.java,
                    "swiss_army_database"
                ).build().also { INSTANCE = it }
            }
        }

    }
}