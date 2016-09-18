package com.jjbanks.learning.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.logging.Logger;

/**
 * 
 * Utility for management of date-time operations. Provided random generation
 * capabilities for use by applications needing to generate random dates and
 * times.
 * 
 * @author jjbanks.com
 *
 */
public final class DateTimeUtil {

    private static final int YEAR_NINETEEN_HUNDRED = 1900;
    private static final int YEAR_TWENTY_TEN = 2010;

    private static Logger logger = Logger
            .getLogger(DateTimeUtil.class.getName());

    /**
     * 
     */
    private DateTimeUtil() {
    }

    /**
     * @return a randomly generated date-time
     */
    public static LocalDateTime generateRandomDateTime() {

        RandomDate rd = new RandomDate(
                LocalDate.of(YEAR_NINETEEN_HUNDRED, 1, 1),
                LocalDate.of(YEAR_TWENTY_TEN, 1, 1));
        logger.info("RandomDate: " + rd);

        LocalDate ld = rd.nextDate();
        logger.info("LocalDate: " + ld);

        RandomTime rt = new RandomTime();
        LocalTime lt = rt.nextTime();
        logger.info("LocalTime: " + lt);

        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        logger.info("LocalDateTime: " + ldt);

        return ldt;
    }

    /**
     * 
     * @param from
     *            the local-date time from constraint
     * @param to
     *            the local-date time to constraint
     * @return local date time randomly generated
     */
    public static LocalDateTime generateRandomDateTime(final LocalDate from,
            final LocalDate to) {

        if (!from.isBefore(to)) {
            throw new IllegalArgumentException(
                    "from local date must be before the 'to' local date");
        }

        RandomDate rd = new RandomDate(from, to);
        LocalDate ld = rd.nextDate();

        RandomTime rt = new RandomTime();
        LocalTime lt = rt.nextTime();
        LocalDateTime ldt = LocalDateTime.of(ld, lt);

        return ldt;
    }

    /**
     * 
     * @param zonedDateTime
     *            original zoned date time
     * @param newZoneId
     *            the new zone identifier
     * @param minutesAdjustment
     *            the minutes to be adjusted from original
     * @return zoned date-time representing change with elapsed minutes
     */
    public static ZonedDateTime changeZoneWithElapsedMinutes(
            final ZonedDateTime zonedDateTime, final String newZoneId,
            final int minutesAdjustment) {

        logger.info("current " + zonedDateTime);
        ZoneId arrivingZone = ZoneId.of(newZoneId);
        ZonedDateTime newDateTime = zonedDateTime
                .withZoneSameInstant(arrivingZone)
                .plusMinutes(minutesAdjustment);

        logger.info("adjusted " + newDateTime);
        return newDateTime;
    }

    /**
     * 
     * @author jjbanks.com
     *
     */
    private static class RandomTime {

        private static final int NANO_MAX = 999;
        private static final int MAX_HOUR = 23;
        private static final int MAX_MINUTE = 59;
        private static final int MAX_SECOND = 59;
        private final Random random;

        /**
         * Constructor with random instantiation.
         */
        RandomTime() {
            this.random = new Random();
        }

        /**
         *
         * @return randomized time
         */
        public LocalTime nextTime() {

            int randomHour = random.nextInt(MAX_HOUR);
            int randomMinute = random.nextInt(MAX_MINUTE);
            int randomSecond = random.nextInt(MAX_SECOND);
            int randomNanosecond = random.nextInt(NANO_MAX);

            return LocalTime.of(randomHour, randomMinute, randomSecond,
                    randomNanosecond);

        }
    }

    /**
     * Representation of random date.
     */
    private static class RandomDate {

        private final LocalDate minDate;
        private final LocalDate maxDate;
        private final Random random;

        /**
         * Constructor using minimum and maximum date range.
         * 
         * @param aMinDate
         *            a minimum date constraint
         * @param aMaxDate
         *            a maximum date constraint
         */
        RandomDate(final LocalDate aMinDate, final LocalDate aMaxDate) {
            this.minDate = aMinDate;
            this.maxDate = aMaxDate;
            this.random = new Random();
        }

        /**
         * @return local-date next randomly generated
         */
        public LocalDate nextDate() {
            int minDay = (int) minDate.toEpochDay();
            int maxDay = (int) maxDate.toEpochDay();
            long randomDay = minDay + random.nextInt(maxDay - minDay);
            return LocalDate.ofEpochDay(randomDay);
        }

        /**
         * The toString representation for random date.
         */
        @Override
        public String toString() {
            return "RandomDate{" + "maxDate=" + maxDate + ", " + "minDate="
                    + minDate + '}';
        }
    }

}
