package br.com.rocketseat.nextlevelweek.plantmanager.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import br.com.rocketseat.nextlevelweek.plantmanager.R

class WaterPlantNotification : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val pendingIntent = NavDeepLinkBuilder(context.applicationContext)
            .setGraph(R.navigation.mobile_navigation)
            .setDestination(R.id.plantManagerTabLayoutFragment)
            .createPendingIntent()


        val message: String? = intent.getStringExtra(PLANT_KEY_NOTIFICATION)
        val id: Int = intent.getIntExtra(PLANT_KEY_NOTIFICATION_ID, 0)

        val plantNotification: Notification = getPlantNotification(message, context, pendingIntent)


        notificationManager.notify(id, plantNotification)
    }

    private fun getPlantNotification(message: String?, context: Context, pendingIntent: PendingIntent): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createPlantNotificationChannel(context)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentText(message)
            .setContentTitle("Heeey,")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setSmallIcon(R.drawable.ic_plant)

        return builder.build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createPlantNotificationChannel(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
            enableVibration(true)
            vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 300, 200, 400)
        }

        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        private const val CHANNEL_ID = "DEFAULT_ID_CHANNEL"
        private const val CHANNEL_NAME = "DEFAULT_NAME_CHANNEL"

        const val PLANT_KEY_NOTIFICATION = "plant_key_notification"
        const val PLANT_KEY_NOTIFICATION_ID = "plant_key_notification_id"
    }
}