package com.jjbanks.learning.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.logging.Logger;

/**
 * 
 * Utility for management of date-time operations.
 * Provided random generation capabilities for use by applications
 * needing to generate random dates and times.
 * 
 * @author jjbanks.com
 *
 */
public class DateTimeUtil {

	private static Logger logger = Logger.getLogger(DateTimeUtil.class.getName());

	/**
	 * 
	 * @return a randomly generated date-time
	 */
	public static LocalDateTime generateRandomDateTime() {

		RandomDate rd = new RandomDate(LocalDate.of(1900, 1, 1), LocalDate.of(2010, 1, 1));
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

	public static LocalDateTime generateRandomDateTime(LocalDate from, LocalDate to) {

		if(!from.isBefore(to)) {
			throw new IllegalArgumentException("from local date must be before the 'to' local date");
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
	 * Representation of random time
	 *
	 */
	private static class RandomTime {

		private final Random random;

		/**
		 * Constructor with random instantiation.
		 */
		public RandomTime() {
			this.random = new Random();
		}

		/**
		 *
		 * @return randomized time
		 */
		public LocalTime nextTime() {

			int randomHour = random.nextInt(23);
			int randomMinute = random.nextInt(59);
			int randomSecond = random.nextInt(59);
			int randomNanosecond = random.nextInt(999);

			return LocalTime.of(randomHour, randomMinute, randomSecond, randomNanosecond);

		}
	}

	/**
	 * Representation of random date
	 */
	private static class RandomDate {

		private final LocalDate minDate;
		private final LocalDate maxDate;
		private final Random random;

		public RandomDate(LocalDate minDate, LocalDate maxDate) {
			this.minDate = minDate;
			this.maxDate = maxDate;
			this.random = new Random();
		}

		public LocalDate nextDate() {
			int minDay = (int) minDate.toEpochDay();
			int maxDay = (int) maxDate.toEpochDay();
			long randomDay = minDay + random.nextInt(maxDay - minDay);
			return LocalDate.ofEpochDay(randomDay);
		}

		@Override
		public String toString() {
			return "RandomDate{" + "maxDate=" + maxDate + ", minDate=" + minDate + '}';
		}
	}

}
