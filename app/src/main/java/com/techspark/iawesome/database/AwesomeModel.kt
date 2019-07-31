package com.techspark.iawesome.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="awesome_model_table")
class AwesomeModel {

        @PrimaryKey(autoGenerate = true)
            var id: Long =0

        @ColumnInfo(name="msg")
            var msg = ""

        @ColumnInfo(name="date")
            var date = ""

        @ColumnInfo(name="time")
            var time=""




}