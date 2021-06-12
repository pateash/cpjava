package live.ashish.cpjava.libs;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarUtilsTest {

    @Test
    public void getDayByDate() {
    //        20th July 1993 - tuesday
        assertEquals("TUESDAY",CalendarUtils.getDayByDate(1993,7,20));

    // 9th august 2010 - monday
        assertEquals("MONDAY",CalendarUtils.getDayByDate(2010,8,9));

    // 27th jan 2019 - sunday
        assertEquals("SUNDAY",CalendarUtils.getDayByDate(2019,1,27));
    }
}
