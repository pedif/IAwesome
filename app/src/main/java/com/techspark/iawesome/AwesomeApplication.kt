package com.techspark.iawesome

import android.app.Application
import android.preference.PreferenceManager
import androidx.core.content.edit
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.techspark.iawesome.workers.AwesomeWorker
import java.util.concurrent.TimeUnit

class AwesomeApplication : Application() {

    private val FIRST_TIME_KEY = "first_time"
    private val workTag = "AwesomeWork"
    override fun onCreate() {
        super.onCreate()

        checkIfFirstTime()
        startPeriodicWork()
    }

    private fun checkIfFirstTime() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        if (pref.getBoolean(FIRST_TIME_KEY, true)) {
            pref.edit {
                putBoolean(FIRST_TIME_KEY, false)
                    .commit() }
            AwesomeNotification.showNotification(this,resources.getString(R.string.welcome_title)
                ,resources.getString(R.string.welcome_message))
        }
    }



    private fun startPeriodicWork() {
        val workManager = WorkManager.getInstance()
        val periodicWorkRequest = PeriodicWorkRequest.Builder(AwesomeWorker::class.java, 6, TimeUnit.HOURS).build()
        workManager.enqueueUniquePeriodicWork(workTag, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest)
    }

}