package pl.maniak.mygoals.utils.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private static SimpleDateFormat twoPartDateFormat = new SimpleDateFormat("dd.MM\nyyyy", Locale.getDefault());

    public static String parseDateToString(Date date) {
        return simpleDateFormat.format(date);
    }

    public static String parseDateToTwoPartString(Date date) {
        return twoPartDateFormat.format(date);
    }
}