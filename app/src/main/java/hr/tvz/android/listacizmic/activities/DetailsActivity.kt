package hr.tvz.android.listacizmic.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.listacizmic.R
import hr.tvz.android.listacizmic.databinding.ActivityDetailsBinding
import hr.tvz.android.listacizmic.models.Transaction

class DetailsActivity : AppCompatActivity() {
    private val TAG = "LABOS"
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var trans: Transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        trans = intent.getParcelableExtra("single_transaction")!!

        setDetails()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    private var isOn = false
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dark_switch -> {
                isOn = !isOn
                item.isChecked = isOn
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
                mIntent.putExtra("uri_src", trans.img_id)
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