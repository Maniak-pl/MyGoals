package pl.maniak.mygoals.utils.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

    public static String parseDateToString(Date date) {
        return simpleDateFormat.format(date);
    }
}