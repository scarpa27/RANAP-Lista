package hr.tvz.android.listacizmic.models;

import static hr.tvz.android.listacizmic.models.TransactionConverter.localDateToString;
import static hr.tvz.android.listacizmic.models.TransactionConverter.stringDateToLocal;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;

import kotlin.random.RandomKt;

@Entity(tableName = "transaction_table")
@TypeConverters({TransactionConverter.class})
public class Transaction {


    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private LocalDate date;
    private Double amount;
    private Uri img;

    //entity constructor
    public Transaction(LocalDate date, Double amount, Uri img) {
        this.date = date;
        this.amount = amount;
        this.img = img;
    }

    public Transaction(String _date, Double _amount) {
        this.date = stringDateToLocal(_date);
        this.amount = _amount;
        this.img = rngUri();
    }

    public String details() {
        return "\nT\n  " + date + "   " + amount + "   " + "   " + img;
    }

    //region getset
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }

    public String getStrDate() {
        return localDateToString(this.date);
    }
    //endregion

    public Uri rngUri() {
        return Uri.parse(new String[]{
                "android.resource://hr.tvz.android.ListaCizmic/drawable/pay1",
                "android.resource://hr.tvz.android.ListaCizmic/drawable/pay2",
                "android.resource://hr.tvz.android.ListaCizmic/drawable/pay3"
        }[RandomKt.Random(System.currentTimeMillis()).nextInt(3)]);
    }
}
