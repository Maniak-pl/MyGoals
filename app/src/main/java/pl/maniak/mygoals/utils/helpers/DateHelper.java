package pl.maniak.mygoals.utils.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static String parseDateToString(Date date) {
        return simpleDateFormat.format(date);
    }
}
