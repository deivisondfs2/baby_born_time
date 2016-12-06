package br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.infra;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by deivisonfrancisco on 01/05/16.
 */
public abstract class InfraUtils {

    public static final String PATTERN_DAY_MONTH_YEAR = "dd/MM/yyyy";

    /*
    convert string for dateTime (dd/MM/yyyy)
     */
    public static DateTime parseDateTimeDayMonthYear(String input){
        String pattern = PATTERN_DAY_MONTH_YEAR;
        DateTime dateTime  = DateTime.parse(input, DateTimeFormat.forPattern(pattern));
        return dateTime;
    }

    public static String getDateFormated(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DAY_MONTH_YEAR);
        String formattedDate = sdf.format(c.getTime());

        return formattedDate;
    }

    public static String getDateTimeFormatedToString(DateTime dateTime) {
        Calendar c = Calendar.getInstance();
        c.set(dateTime.getYear(), dateTime.getMonthOfYear() - 1, dateTime.getDayOfMonth());

        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DAY_MONTH_YEAR);
        String formattedDate = sdf.format(c.getTime());

        return formattedDate;
    }

}
