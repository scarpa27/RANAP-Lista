package hr.tvz.android.listacizmic.models;

import android.net.Uri;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionConverter {
    private final static String DATE_PATTERN = "yyyy.MM.dd.";

    @TypeConverter
    public static LocalDate stringDateToLocal(String _date) {
        return LocalDate.parse(_date, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    @TypeConverter
    public static String localDateToString(LocalDate _date) {
        return _date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    @TypeConverter
    public static String uri2string(Uri uri) {
        return uri.toString();
    }

    @TypeConverter
    public static Uri string2uri(String string) {
        return Uri.parse(string);
    }

}
