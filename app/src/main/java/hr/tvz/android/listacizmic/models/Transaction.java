package hr.tvz.android.listacizmic.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction implements Parcelable {
    private final static String DATE_PATTERN = "dd.MM.yyyy.";

    private Integer id;
    private LocalDate date;
    private Double amount;
    private Uri img;
    private Integer img_id;

    public Transaction(Integer _id, String _date, Double _amount, Integer _img_id) {
        this.id = _id;
        this.date = stringDateToLocal(_date);
        this.amount = _amount;
//        this.img = _img;
        this.img_id = _img_id;
    }


    protected Transaction(Parcel in) {
        this.id = in.readInt();
        this.date = this.stringDateToLocal(in.readString());
        this.amount = in.readDouble();
        this.img = Uri.parse(in.readString());
        this.img_id = in.readInt();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public static LocalDate stringDateToLocal(String _date) {
        return LocalDate.parse(_date, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static String localDateToString(LocalDate _date) {
        return _date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
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

    public String getStrDate() {return localDateToString(this.date);}

    public Integer getImg_id() {
        return img_id;
    }

    public void setImg_id(Integer img_id) {
        this.img_id = img_id;
    }
    //endregion

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.localDateToString(this.date));
        parcel.writeDouble(this.amount);
        parcel.writeString(this.img.toString());
        parcel.writeInt(this.img_id);
    }
}
