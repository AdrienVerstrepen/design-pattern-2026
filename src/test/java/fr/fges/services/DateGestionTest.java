package fr.fges.services;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import static fr.fges.services.DateGestion.isWeekEnd;
import static org.junit.jupiter.api.Assertions.*;

public class DateGestionTest {
    @Test
    void testIsWeekEndTrue(){
        // this function tests if the function detects correctly the days that are weekends
        int weekDay = 1;
        boolean resultat = isWeekEnd(weekDay);
        assertTrue(resultat);
    }

    @Test
    void testIsWeekEndFalse(){
        // this function tests if the function detects correctly the days that are not weekends
        int weekDay = 2;
        boolean resultat = isWeekEnd(weekDay);
        assertFalse(resultat);
    }

    @Test
    void testGetWeekDay() {
        // this function tests if getWeekDay matches the calendar weekday
        Calendar calendar = DateGestion.getDate();
        int previsionWeekDay = calendar.get(Calendar.DAY_OF_WEEK);
        int resultat = DateGestion.getWeekDay();
        assertEquals(previsionWeekDay, resultat);
    }
}