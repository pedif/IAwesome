package com.techspark.iawesome.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.*

@Entity(tableName="awesome_model_table")
class AwesomeModel {

        @PrimaryKey(autoGenerate = true)
            var id: Long =0

        @ColumnInfo(name="msg")
            var msg = ""

        @ColumnInfo(name="date")
            var date: String = DateFormat.getDateInstance().format(Date())

        @ColumnInfo(name="time")
            var time: String =DateFormat.getDateTimeInstance().format(Date())




}