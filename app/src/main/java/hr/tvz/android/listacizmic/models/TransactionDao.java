package hr.tvz.android.listacizmic.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert
    void insert(Transaction t);

    @Query("SELECT COUNT(date) AS cnt FROM transaction_table")
    LiveData<Integer> getCount();

    @Query("SELECT * FROM transaction_table ORDER BY date DESC")
    LiveData<List<Transaction>> getAll();

    @Query("SELECT * FROM TRANSACTION_TABLE WHERE id=:id")
    LiveData<Transaction> getById(Integer id);
}
