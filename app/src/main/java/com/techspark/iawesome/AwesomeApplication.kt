package com.techspark.iawesome

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.techspark.iawesome.workers.AwesomeWorker
import java.util.concurrent.TimeUnit

class AwesomeApplication : Application() {

    private val workTag = "AwesomeWork"
    override fun onCreate() {
        super.onCreate()

        startWorkManager()
    }

    private fun startWorkManager() {
        val workManager = WorkManager.getInstance()
        val periodicWorkRequest = PeriodicWorkRequest.Builder(AwesomeWorker::class.java, 6, TimeUnit.HOURS).build()
        workManager.enqueueUniquePeriodicWork(workTag, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest)
    }

}