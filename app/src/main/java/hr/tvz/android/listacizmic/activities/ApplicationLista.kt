package hr.tvz.android.listacizmic.activities

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import hr.tvz.android.listacizmic.utils.CustomBroadcastReceiver

class ApplicationLista : Application() {
    val rec = CustomBroadcastReceiver();

    companion object {
        private var instance: Application? = null
        fun instance(): Application? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(rec, filter)

    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterReceiver(rec)
    }

}