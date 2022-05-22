package hr.tvz.android.listacizmic.models;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ListViewModel extends AndroidViewModel {
    private TransactionRepo repo;
    private LiveData<List<Transaction>> allTransactions;

    public ListViewModel(@NonNull Application application) {
        super(application);
        Log.d("LABOS", "TransactionViewModel počeo");
        repo = new TransactionRepo(application);
        allTransactions = repo.getAllTransactions();
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<Integer> getCount() {
        return repo.getCount();
    }
}
