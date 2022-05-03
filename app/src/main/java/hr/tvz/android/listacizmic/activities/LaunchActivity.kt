package hr.tvz.android.listacizmic.activities

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.util.Log
import hr.tvz.android.listacizmic.R
import hr.tvz.android.listacizmic.models.Transaction
import hr.tvz.android.listacizmic.utils.Util
import kotlin.random.Random

class LaunchActivity : Activity() {
    private val TAG = "LABOS"
    private val TRNS_LIST = "transaction_list"
    private lateinit var transactions: List<Transaction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "LaunchActivity started")


        val rnd = Random(System.currentTimeMillis())
        val ids = ArrayList<Int>()
        ids.add(R.drawable.pay1)
        ids.add(R.drawable.pay2)
        ids.add(R.drawable.pay3)
        



        transactions = arrayListOf(
            Transaction(1, "19.04.2022.", 65.33, ids[rnd.nextInt(3)]),
            Transaction(2, "22.04.2022.", 125.23, ids[rnd.nextInt(3)]),
            Transaction(3, "19.03.2022.", 465.33, ids[rnd.nextInt(3)]),
            Transaction(4, "09.04.2022.", 53.93, ids[rnd.nextInt(3)]),
            Transaction(5, "10.04.2022.", 2510.18, ids[rnd.nextInt(3)]),
            Transaction(2, "22.04.2022.", 125.23, ids[rnd.nextInt(3)]),
            Transaction(3, "19.03.2022.", 465.33, ids[rnd.nextInt(3)]),
            Transaction(4, "09.04.2022.", 53.93, ids[rnd.nextInt(3)]),
            Transaction(5, "10.04.2022.", 2510.18, ids[rnd.nextInt(3)]),
            Transaction(2, "22.04.2022.", 125.23, ids[rnd.nextInt(3)]),
            Transaction(3, "19.03.2022.", 465.33, ids[rnd.nextInt(3)]),
            Transaction(4, "09.04.2022.", 53.93, ids[rnd.nextInt(3)]),
            Transaction(5, "10.04.2022.", 2510.18, ids[rnd.nextInt(3)])
        )
        transactions.forEach { transaction -> transaction.img = Util.popuri(resources, transaction.img_id) }

        for (t in transactions) {
            Log.d(TAG, "uri " + t.img)
        }


        val intent: Intent = Intent(this, ListActivity::class.java)
        intent.putParcelableArrayListExtra(TRNS_LIST, transactions as ArrayList<Transaction>)
        startActivity(intent)

    }


}