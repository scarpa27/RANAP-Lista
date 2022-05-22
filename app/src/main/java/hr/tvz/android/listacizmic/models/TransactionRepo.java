package hr.tvz.android.listacizmic.models;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TransactionRepo {
    private TransactionDao dao;
    private LiveData<List<Transaction>> allTransactions;
    private LiveData<Transaction> oneTransaction;

    public TransactionRepo(Application app) {
        TransactionDatabase db = TransactionDatabase.getInstance(app);
        dao = db.transactionDao();
        allTransactions = dao.getAll();
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<Transaction> getOneTransaction(Integer id) {
        oneTransaction = dao.getById(id);
        return oneTransaction;
    }

    public LiveData<Integer> getCount() {
        return dao.getCount();
    }
}
