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

    

    init {
        val workManager = WorkManager.getInstance()
        val periodicWorkRequest = PeriodicWorkRequest.Builder(AwesomeWorker::class.java, 60, TimeUnit.SECONDS).build()
        workManager.enqueue(periodicWorkRequest)
    }
}
