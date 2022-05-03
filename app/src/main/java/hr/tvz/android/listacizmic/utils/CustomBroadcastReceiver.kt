package hr.tvz.android.listacizmic.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class CustomBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED == intent.action) {
            Toast.makeText(context, "Flight mode toggled " + onoff(context), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun onoff(context: Context): String {
        return if (Settings.System.getInt(
                context.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON,
                0
            ) == 0
        ) "off" else "on"
    }
}