package hr.tvz.android.listacizmic.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import hr.tvz.android.listacizmic.R
import hr.tvz.android.listacizmic.databinding.ActivityDetailsBinding
import hr.tvz.android.listacizmic.models.Transaction
import kotlin.math.log
import kotlin.properties.Delegates

class DetailsActivity : AppCompatActivity() {
    private val TAG = "LABOS"
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var trans: Transaction
    private var trans_id = 0
    private var darkMode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "dark value:  "+applicationContext.resources.configuration.uiMode.toString())
        if (darkMode == 0) darkMode = applicationContext.resources.configuration.uiMode


//        trans = intent.getParcelableExtra("single_transaction")!!
        trans_id = intent.extras?.get("single_transaction") as Int

        setDetails()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        menu?.findItem(R.id.dark_switch)?.isChecked = darkMode == 33
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dark_switch -> {
                darkMode = when (darkMode) {
                    17 -> 33
                    33 -> 17
                    else -> 17
                }
                item.isChecked = darkMode == 33
                AppCompatDelegate.setDefaultNightMode(if (darkMode == 33) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
            }
            R.id.share -> {
                shareToWhatsApp()
            }
        }
        return true
    }

    private fun setDetails() {
        binding.tvVarId.text = trans.id.toString()
        binding.tvVarAmount.text = String.format("%.2f HRK", trans.amount)
        binding.tvVarDate.text = trans.strDate
        binding.detailImage.setImageURI(trans.img)
        binding.detailImage.setOnLongClickListener {
            kotlin.run {
                val mIntent = Intent(this@DetailsActivity, ImageActivity::class.java)
//                mIntent.putExtra("uri_src", trans.img_id)
                startActivity(mIntent)
            }
            true
        }
    }

    private fun shareToWhatsApp() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "ID: "+trans.id.toString()+"\n"+
                    trans.amount+" HRK\n"+
                    ""+trans.strDate+"\n\n"
            +"sent through my android application"
        )
        shareIntent.setPackage("com.whatsapp")
        shareIntent.type = "text/plain"
        startActivity(shareIntent)
    }
}