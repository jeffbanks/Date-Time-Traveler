package com.jjbanks.learning.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.jjbanks.learning.util.DateTimeUtil;

/**
 * Verification of the TimeUtil operations.
 * 
 * @author jjbanks.com
 *
 */
public class TimeUtilTest {

    @Test
    public void generateRandomDateTimeDefault() {

        LocalDateTime ldt = DateTimeUtil.generateRandomDateTime();
        assertTrue(ldt != null);
        assertTrue(ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) != null);

    }

    @Test
    public void generateRandomDateTimeCustom() {

        LocalDateTime ldt = DateTimeUtil.generateRandomDateTime(
                LocalDate.of(1900, 1, 1), LocalDate.of(1900, 1, 10));

        assertTrue(ldt != null);
        assertTrue(ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) != null);

        assertTrue(ldt.getYear() == 1900);
        assertTrue(ldt.getMonthValue() == 1);
        assertTrue(ldt.getDayOfMonth() >= 1 && ldt.getDayOfMonth() <= 10);

    }

    @Test
    public void randomDateTimeWithIncorrectFromTo() {
        @SuppressWarnings("unused")
        LocalDateTime ldt = null;

        try {

            ldt = DateTimeUtil.generateRandomDateTime(LocalDate.of(1900, 1, 10),
                    LocalDate.of(1900, 1, 1));
            fail("Expecting out of order to/from to generate an exception");
        } catch (Exception e) {
            assertTrue(e != null);
        }

        try {
            // try an invalid day of month -2
            ldt = DateTimeUtil.generateRandomDateTime(LocalDate.of(1900, 1, -2),
                    LocalDate.of(1900, 1, 10));
            fail("Expecting to generate an exception");
        } catch (Exception e) {
            assertTrue(e != null);
        }

    }

    @Test
    public void generateChangeZoneWithElapsedMinutes() {

        LocalDate ld = LocalDate.of(2016, 10, 13);
        LocalTime lt = LocalTime.of(14, 12, 24, 192829);

        String zoneIdentifier = "America/Los_Angeles";
        ZonedDateTime zonedDateTime = ZonedDateTime.of(ld, lt,
                ZoneId.of(zoneIdentifier));

        String newZoneIdentifier = "America/Chicago";
        int minutesAdjustment = 120;

        ZonedDateTime zdt = DateTimeUtil.changeZoneWithElapsedMinutes(
                zonedDateTime, newZoneIdentifier, minutesAdjustment);

        assertTrue(zdt.getDayOfMonth() == 13);
        assertTrue(zdt.getMonthValue() == 10);
        assertTrue(zdt.getYear() == 2016);

        assertTrue(zdt.toLocalTime().getHour() == 18);
        assertTrue(zdt.toLocalTime().getMinute() == 12);
        assertTrue(zdt.toLocalTime().getSecond() == 24);
        assertTrue(zdt.toLocalTime().getNano() == 192829);
    }

}
