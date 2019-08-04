package com.techspark.iawesome

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import androidx.core.app.NotificationCompat

class AwesomeNotification {

    /**
     * Displays or updates app notification with new message
     */
    companion object {
        fun showNotification(context: Context, task: String, desc: String) {

            if (!PreferenceManager.getDefaultSharedPreferences(context).getBoolean("notification", true))
                return

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


            val channelId = "task_channel"
            val channelName = "task_name"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
                manager.createNotificationChannel(channel)
            }

            val builder = NotificationCompat.Builder(context, channelId)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher)

            manager.notify(1, builder.build())

        }
    }
}