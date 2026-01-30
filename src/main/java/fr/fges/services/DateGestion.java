package fr.fges.services;
import java.util.Calendar;
import java.util.Date;

public class DateGestion {
    public static boolean isWeekEnd(int weekDay){
        return weekDay == 1 || weekDay == 7;
    }

    public static Calendar getDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    public static int getWeekDay() {
        Calendar calendar = getDate();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}