package com.techspark.iawesome.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AwesomeDao {

    @Insert
    fun insert(awesomeModel: AwesomeModel)

    @Query("SELECT * FROM awesome_model_table WHERE date = :today ORDER BY id DESC")
        fun getMessagesOfDate(today: String):LiveData<List<AwesomeModel>>

}