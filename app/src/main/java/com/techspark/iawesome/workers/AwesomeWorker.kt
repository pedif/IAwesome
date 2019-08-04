package com.techspark.iawesome.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.techspark.iawesome.AwesomeNotification
import com.techspark.iawesome.R
import com.techspark.iawesome.database.AwesomeDatabase
import com.techspark.iawesome.database.AwesomeModel
import java.text.DateFormat
import java.util.*


class AwesomeWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams){

    override fun doWork(): Result {

        //Don't do anything if gender hasn't been set yet
        if (!PreferenceManager.getDefaultSharedPreferences(applicationContext).contains("gender"))
            return Result.success()

        if(isPastNightTime())
            return Result.success()

        val awesomeModel = addNewMessage()
         AwesomeNotification.showNotification(applicationContext, "Insert", awesomeModel.date +"---"+awesomeModel.time)
        return Result.success()
    }

    /**
     * Checks the hour to see if it's night time
     * we don't want to show an awesome message if it's night time
     */
    private fun isPastNightTime(): Boolean {

        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        if(hour in 6..22)
            return false
        return true
    }

    /**
     * Inserts a new message to the database with current date and time
     */
    private fun addNewMessage(): AwesomeModel {
        val awesomeModel = AwesomeModel()
        awesomeModel.msg = "Whatever"
        awesomeModel.date = DateFormat.getDateInstance().format(Date())
        awesomeModel.time = DateFormat.getTimeInstance().format(Date())
        AwesomeDatabase.getInstance(applicationContext).awesomeDao.insert(awesomeModel)
        return  awesomeModel
    }



}