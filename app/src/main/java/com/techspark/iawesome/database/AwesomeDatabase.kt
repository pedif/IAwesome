package com.techspark.iawesome.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AwesomeModel::class], version = 1, exportSchema = false)
abstract class AwesomeDatabase: RoomDatabase() {

    abstract val awesomeDao: AwesomeDao

    companion object {
        @Volatile
        private var INSTANCE: AwesomeDatabase? = null

        fun getInstance(context: Context):AwesomeDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AwesomeDatabase::class.java,
                        "awesome_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}