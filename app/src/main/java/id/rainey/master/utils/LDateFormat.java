package id.rainey.master.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LDateFormat {

    public static String changeToIndonesianMonthName(int month) {
        switch (month) {
            case 1:
                return "Januari";
            case 2:
                return "Februari";
            case 3:
                return "Maret";
            case 4:
                return "April";
            case 5:
                return "Mei";
            case 6:
                return "Juni";
            case 7:
                return "Juli";
            case 8:
                return "Agustus";
            case 9:
                return "September";
            case 10:
                return "Oktober";
            case 11:
                return "November";
            case 12:
                return "Desember";
            default:
                return "Januari";
        }
    }

    public static String changeToIndonesianDayName(int day) {
        switch (day) {
            case 1:
                return "Minggu";
            case 2:
                return "Senin";
            case 3:
                return "Selasa";
            case 4:
                return "Rabu";
            case 5:
                return "Kamis";
            case 6:
                return "Jumat";
            case 7:
                return "Sabtu";
            default:
                return null;
        }
    }

    public static String changeToMonthName(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return null;
        }
    }

    public static String changeToDayName(int day) {
        switch (day) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return null;
        }
    }

    public static String currentDate() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
    }

    public static String currentDateAndTime() {
        Calendar cal = Calendar.getInstance();
        String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
        String time = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);

        return date + " " + time;
    }

    public static String combineDate(String startDate, String endDate) {
        if (startDate == null || endDate == null)
            return "";

        String[] startDates = startDate.split("-"); // index 0 : years, 1 : month, 2 : date
        String[] endDates = endDate.split("-");

        if (startDate.equals(endDate)) {
            String month = changeToMonthName(Integer.parseInt(startDates[1]));
            return (startDates[2] + " " + month + " " + startDates[0]);
        }

        if (startDates[0].equals(endDates[0])) { // Has the same years
            if (startDates[1].equals(endDates[1])) { // Has the same month in the same years (ex : 1-3 Dec 2014)
                String month = changeToMonthName(Integer.parseInt(startDates[1]));
                return (startDates[2] + " - " + endDates[2] + " " + month + " " + startDates[0]);
            } else { // Has different month in the same years (ex : 1 Nov - 3 Dec 2014)
                String startMonth = changeToMonthName(Integer.parseInt(startDates[1]));
                String endMonth = changeToMonthName(Integer.parseInt(endDates[1]));

                return (startDates[2] + " " + startMonth + " - " + endDates[2] + " " + endMonth + " " + startDates[0]);
            }
        } else { // Has different years (ex : 1 Dec 2014 - 1 Dec 2015)
            String startMonth = changeToMonthName(Integer.parseInt(startDates[1]));
            String endMonth = changeToMonthName(Integer.parseInt(endDates[1]));

            String start = startDates[2] + " " + startMonth + " " + startDates[0];
            String end = endDates[2] + " " + endMonth + " " + endDates[0];

            return (start + " - " + end);
        }
    }

    private static Date getDateObject(String dates, String dateFormat) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);

        try {
            date = format.parse(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String detailedDate(String dates, boolean includeDayOfWeek) {
        Date date = getDateObject(dates, "yyyy-MM-dd");

        // Refer to http://developer.android.com/reference/java/text/SimpleDateFormat.html
        //String intMonth = (String) DateFormat.format("MM", date); //06
        String month = (String) DateFormat.format("MMM", date); //Jan
        String year = (String) DateFormat.format("yyyy", date); //2013
        String day = (String) DateFormat.format("dd", date); //20
        String dayOfWeek = (String) DateFormat.format("EEEE", date); //Thursday

        if (includeDayOfWeek)
            return dayOfWeek + ", " + day + " " + month + " " + year;
        else return day + " " + month + " " + year;
    }

    public static String getIndonesianDetailedDate(String dates, boolean includeDayOfWeek) {
        Date date = getDateObject(dates, "yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String day = (String) DateFormat.format("dd", date); //20
        String intMonth = (String) DateFormat.format("MM", date); //06
        String year = (String) DateFormat.format("yyyy", date); //2013

        String month = changeToIndonesianMonthName(Integer.parseInt(intMonth));

        if (includeDayOfWeek) {
            int dayOfWeekNumber = cal.get(Calendar.DAY_OF_WEEK); // Day of week index (Sunday is 1)
            String dayOfWeek = changeToIndonesianDayName(dayOfWeekNumber);

            return dayOfWeek + ", " + day + " " + month + " " + year;
        } else return day + " " + month + " " + year;
    }

    public static boolean endTimeIsGreaterThanStartTime(String time1, String time2) {
        String[] times1 = time1.split(":");
        String[] times2 = time2.split(":");

        int seconds1 = (Integer.parseInt(times1[0]) * 3600) + ((Integer.parseInt(times1[1]) * 60));
        int seconds2 = (Integer.parseInt(times2[0]) * 3600) + ((Integer.parseInt(times2[1]) * 60));

        return (seconds2 > seconds1) ? true : false;
    }

    public static boolean endDateisGreaterThanStartDate(String dates1, String dates2) {
        Date date1 = getDateObject(dates1, "yyyy-MM-dd HH:mm");
        Date date2 = getDateObject(dates2, "yyyy-MM-dd HH:mm");

        // If dates doesn't contain hour
        if (!dates1.contains(":")) {
            date1 = getDateObject(dates1, "yyyy-MM-dd");
            date2 = getDateObject(dates2, "yyyy-MM-dd");
        }

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        return (time2 > time1) ? true : false;
    }

    // Get time range from today to a past date
    public static String timeRangeUntilToday(String pastDate) {
        Date d1 = getDateObject(pastDate, "yyyy-MM-dd HH:mm:ss");
        Date d2 = getDateObject(currentDateAndTime(), "yyyy-MM-dd HH:mm:ss");

        //in milliseconds
        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000) % 7;
        long diffWeeks = diff / (7 * 24 * 60 * 60 * 1000);

        if (diffWeeks > 0) return diffWeeks + "w";
        else if (diffDays > 0) return diffDays + "d";
        else if (diffHours > 0) return diffHours + "h";
        else if (diffMinutes > 0) return diffMinutes + "m";
        else return (diffSeconds < 10) ? "Just now" : diffSeconds + "s";
    }

    // Get time range from today to a future date in day format
    public static long timeRangeFromTodayInDay(String endDate) {
        Date d1 = getDateObject(currentDateAndTime(), "yyyy-MM-dd");
        Date d2 = getDateObject(endDate, "yyyy-MM-dd");

        //in milliseconds
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays;
    }

    public static long timeRangeInDay(String startDate, String endDate) {
        Date d1 = getDateObject(startDate, "yyyy-MM-dd");
        Date d2 = getDateObject(endDate, "yyyy-MM-dd");

        //in milliseconds
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) % 7;

        return diffDays;
    }
}
