package hr.tvz.android.listacizmic.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import hr.tvz.android.listacizmic.R
import hr.tvz.android.listacizmic.databinding.ActivityListBinding
import hr.tvz.android.listacizmic.links.TransactionsAdapter
import hr.tvz.android.listacizmic.models.Transaction
import hr.tvz.android.listacizmic.models.ListViewModel
import hr.tvz.android.listacizmic.utils.Util
import java.time.LocalDate

class ListActivity : AppCompatActivity() {
    private val TRNSLIST = "transaction_list"
    private val TAG = "LABOS"

    private lateinit var viewmodel: ListViewModel

    private lateinit var binding: ActivityListBinding
    private var list: ArrayList<Transaction> = ArrayList()
    private lateinit var rview: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
        tokenLogging()
//
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
        viewmodel = ViewModelProvider(this)[ListViewModel::class.java]
//
        recyclerSetup()
//
        observeRoom()
    }
//
//
//
    private fun recyclerSetup() {
        rview = binding.recycler
        rview.layoutManager = LinearLayoutManager(applicationContext)
        rview.itemAnimator = DefaultItemAnimator()
        rview.adapter = adapterSetup()
    }

    private fun adapterSetup(): TransactionsAdapter {
        val adapt = TransactionsAdapter(list)
        adapt.setTransactionClickHandler { pos: Int ->
            run {
                Toast.makeText(this, "Kliknuto na $pos ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ListActivity, DetailsActivity::class.java)
                intent.putExtra("single_transaction", list[pos].id)
                startActivity(intent)
            }
        }
        return adapt
    }

    private fun observeRoom() {
        viewmodel.count.observe(this) {
            run { Log.d(TAG, "trenutni broj zapisa u lokalnoj bazi: " + it) }
        }

        viewmodel.allTransactions.observe(this) {
            list = it as ArrayList<Transaction>
            (rview.adapter as TransactionsAdapter).setTransactions(it)
            list.forEach {
                run { Log.d(TAG, it.details()) }
            }
        }
        list.add(Transaction(LocalDate.now(), 55.55, Util.popuri(resources, R.drawable.pay1)))
    }

    private fun tokenLogging() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            val msg = "token iz instance--->  $token"
            Log.d(TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
        })
    }

}