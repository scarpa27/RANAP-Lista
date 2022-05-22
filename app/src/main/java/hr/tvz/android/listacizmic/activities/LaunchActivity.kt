package hr.tvz.android.listacizmic.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import hr.tvz.android.listacizmic.R

class LaunchActivity : Activity() {
    private val TAG = "LABOS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "LaunchActivity started")

        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}