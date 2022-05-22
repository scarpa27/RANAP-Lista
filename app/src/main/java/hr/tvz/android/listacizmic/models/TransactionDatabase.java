package hr.tvz.android.listacizmic.models;

import static kotlin.random.RandomKt.Random;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.random.Random;

@Database(entities = {Transaction.class}, version = 1, exportSchema = false)
public abstract class TransactionDatabase extends RoomDatabase {

    private static TransactionDatabase instance;
    private static final RoomDatabase.Callback populateCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            populate();
        }
    };

    public static synchronized TransactionDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TransactionDatabase.class,
                    "transaction_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(populateCallback).build();
        return instance;
    }

    private static void populate() {
        Random rnd = Random(System.currentTimeMillis());
        TransactionDao dao = instance.transactionDao();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            dao.insert(new Transaction("2022.04.19.", 65.33));
            dao.insert(new Transaction("2022.04.22.", 125.23));
            dao.insert(new Transaction("2022.03.19.", 465.33));
            dao.insert(new Transaction("2022.04.09.", 53.93));
            dao.insert(new Transaction("2022.04.10.", 2510.18));
            dao.insert(new Transaction("2022.04.22.", 125.23));
            dao.insert(new Transaction("2022.03.19.", 465.33));
            dao.insert(new Transaction("2022.04.09.", 53.93));
            dao.insert(new Transaction("2022.04.10.", 2510.18));
            dao.insert(new Transaction("2022.04.22.", 125.23));
            dao.insert(new Transaction("2022.03.19.", 465.33));
            dao.insert(new Transaction("2022.04.09.", 53.93));
            dao.insert(new Transaction("2022.04.10.", 2510.18));
        });
    }

    public abstract TransactionDao transactionDao();
}
