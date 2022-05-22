package hr.tvz.android.listacizmic.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import hr.tvz.android.listacizmic.R
import hr.tvz.android.listacizmic.R.id

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        findViewById<TextView>(id.tv_1).text = intent.getStringExtra("title")
        findViewById<TextView>(id.tv_2).text = intent.getStringExtra("text")
    }
}