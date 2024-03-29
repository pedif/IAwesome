package com.techspark.iawesome

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.techspark.iawesome.database.AwesomeDatabase
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class AwesomeViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AwesomeDatabase
        .getInstance(application)
        .awesomeDao

    val messages = database
        .getMessagesOfDate(getToday())

    private fun getToday(): String {

        return DateFormat.getDateInstance().format(Date())
    }

}
