package lilee.hd.anotterredditapptwo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConverterUtil {


    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }


    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String convertUnixToDate(Long unixDate) {
        Date date = new Date(unixDate * 1000);
        return new SimpleDateFormat("MMM dd, yyyy hh:mma", Locale.getDefault()).format(date);
    }
}

