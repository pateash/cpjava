package live.ashish.codejava.libs;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarUtils {

    /*  note that year starts from 1900 as 0 , month 0 for January and so on,
     day is 1 to 31 according to month*/
    static public String getDayByDate(int year,int month,int day){

        // so we can just pass actual year from callmonth=month-1;
        month--;

        GregorianCalendar calendar=new GregorianCalendar(year,month,day);
        // 0 will never be returned, 1 sunday and 7 for saturday
        String[] days =
                {"","SUNDAY","MONDAY","TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};

        return days[calendar.get(Calendar.DAY_OF_WEEK)];
    }

}
