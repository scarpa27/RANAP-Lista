package hr.tvz.android.listacizmic.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.listacizmic.databinding.ActivityListBinding
import hr.tvz.android.listacizmic.links.TransactionsAdapter
import hr.tvz.android.listacizmic.models.Transaction

class ListActivity : AppCompatActivity() {
    private val TRNSLIST = "transaction_list"
    private val TAG = "LABOS"

    private lateinit var binding: ActivityListBinding
    private lateinit var list: ArrayList<Transaction>
    private lateinit var rview: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = intent.getParcelableArrayListExtra<Transaction>(TRNSLIST)!!

        recyclerSetup()

    }

    private fun recyclerSetup() {
        rview = binding.recycler
        rview.layoutManager = LinearLayoutManager(applicationContext)
        rview.itemAnimator = DefaultItemAnimator()
        val adapt = TransactionsAdapter(list)
        adapt.setTransactionClickHandler { pos: Int ->
            run {
                Toast.makeText(this, "Kliknuto na $pos ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ListActivity, DetailsActivity::class.java)
                intent.putExtra("single_transaction", list[pos])
                startActivity(intent)
            }
        }
        rview.adapter = adapt
    }

}