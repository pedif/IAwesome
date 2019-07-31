package com.techspark.iawesome

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.core.app.NotificationCompat
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build



class AwesomeWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams){

    override fun doWork(): Result {
        
        return Result.success()
    }


    private fun showNotification(task: String, desc: String) {

        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val channelId = "task_channel"
        val channelName = "task_name"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle(task)
            .setContentText(desc)
            .setSmallIcon(R.mipmap.ic_launcher)

        manager.notify(1, builder.build())

    }
}