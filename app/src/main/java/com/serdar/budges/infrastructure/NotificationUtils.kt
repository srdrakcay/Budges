package com.serdar.budges.infrastructure

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import com.serdar.budges.R
import com.serdar.budges.util.Constants.Companion.CHANNEL_ID
import com.serdar.budges.util.Constants.Companion.CHANNEL_NAME

object NotificationUtils {

    fun budgesNotification(context: Context, title: String, description: String) {
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        createBudgesNotificationChannel(notificationManager)

        val builder = createBudgesNotificationCompat(context, title, description)

        notificationManager.notify(1, builder.build())
    }

    private fun createBudgesNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createBudgesNotificationCompat(
        context: Context,
        title: String,
        description: String
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.bitcoin)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
    }
}
