package com.example.bookmanagementsystem.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static boolean isDateInPast(Date dateToCheck) {
        // Create a Calendar instance for the current date and time
        Calendar currentDate = Calendar.getInstance();

        // Set the time to the current date
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);

        // Create a Calendar instance for the dateToCheck
        Calendar dateToCheckCalendar = Calendar.getInstance();
        dateToCheckCalendar.setTime(dateToCheck);

        // Compare dateToCheck with the current date
        return dateToCheckCalendar.before(currentDate);
    }
}
