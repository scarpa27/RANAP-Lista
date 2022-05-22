package hr.tvz.android.listacizmic.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import hr.tvz.android.listacizmic.R
import hr.tvz.android.listacizmic.activities.TestActivity

class PushService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        val title = message.notification!!.title
        val text = message.notification!!.body
        val CHANNEL_ID = "TONI_NOTIFICATION"
        Log.d("LABOS", title!!)

       val channel =  NotificationChannel(
            CHANNEL_ID,
            "zazznuta notifikacija",
           NotificationManager.IMPORTANCE_HIGH
        )

        val intent = Intent(this, TestActivity::class.java)
        intent.putExtra("title",title)
        intent.putExtra("text", text)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


        val pint = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val note = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.pay1)
            .setColor(Color.argb(128, 255, 80, 120))
            .setContentIntent(pint)
            .setAutoCancel(false)

        NotificationManagerCompat.from(this).notify(5, note.build())

        super.onMessageReceived(message)
    }

    override fun onNewToken(token: String) {
        Log.d("LABOS", "new token -----> $token")
        super.onNewToken(token)
    }
}