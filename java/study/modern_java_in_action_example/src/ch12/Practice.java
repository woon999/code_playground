package ch12;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Practice {

	@Test
	void locatdate(){
		LocalDate date = LocalDate.of(2022, 2, 2);
		int year = date.getYear(); // 2022
		Month month = date.getMonth(); // FEBRUARY
		int day =date.getDayOfMonth(); // 2

		assertEquals(year, 2022);
		assertEquals(month, Month.FEBRUARY);
		assertEquals(day, 2);

		DayOfWeek dow = date.getDayOfWeek(); // WEDNESDAY
		int len = date.lengthOfMonth(); // 28 (2월 일 수)
		boolean leap = date.isLeapYear(); // 윤년이 아님

		assertEquals(dow, DayOfWeek.WEDNESDAY);
		assertEquals(len, 28);
		assertEquals(leap, false);
	}

	@Test
	void temporalField(){
		LocalDate date = LocalDate.of(2022, 2, 2);
		int year = date.get(ChronoField.YEAR);
		int month = date.get(ChronoField.MONTH_OF_YEAR);
		int day = date.get(ChronoField.DAY_OF_MONTH);

		assertEquals(year, 2022);
		assertEquals(month, 2);
		assertEquals(day, 2);
	}

	@Test
	void localtime(){
		LocalTime time = LocalTime.of(13, 45, 20);
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();

		assertEquals(hour, 13);
		assertEquals(minute, 45);
		assertEquals(second, 20);
	}

	@Test
	void localdatetime(){
		// 2022-02-02T13:45:20
		String answer = "2022-02-02T13:45:20";
		LocalDateTime dt1 = LocalDateTime.of(2022, Month.FEBRUARY, 2, 13, 45, 20);

		LocalDate date = LocalDate.of(2022, 2, 2);
		LocalTime time = LocalTime.of(13, 45, 20);
		LocalDateTime dt2 = LocalDateTime.of(date, time);

		LocalDateTime dt3 = date.atTime(13, 45 ,20);
		LocalDateTime dt4 = date.atTime(time);
		LocalDateTime dt5 = time.atDate(date);

		assertEquals(dt1.toString(), answer);
		assertEquals(dt2.toString(), answer);
		assertEquals(dt3.toString(), answer);
		assertEquals(dt4.toString(), answer);
		assertEquals(dt5.toString(), answer);
	}

	@Test
	void instant(){
		Instant instant = Instant.ofEpochSecond(3); // 3s
		System.out.println("instant = " + instant);

		instant = Instant.ofEpochSecond(3, 0); // 3s
		System.out.println("instant = " + instant);

		instant = Instant.ofEpochSecond(2, 1_000_000_000); // 2s + 1억나노초(1s) = 3s
		System.out.println("instant = " + instant);

		instant = Instant.ofEpochSecond(4, -1_000_000_000); // 4s - 1억나노초(1s) = 3s
		System.out.println("instant = " + instant);
	}

	@Test
	void durationAndPeriod(){
		Duration threeMinutes = Duration.ofMinutes(3);
		Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
		assertEquals(threeMinutes, threeMinutes2);

		Period tenDays = Period.ofDays(10);
		System.out.println("tenDays = " + tenDays);
		Period threeWeeks = Period.ofWeeks(3);
		System.out.println("threeWeeks = " + threeWeeks);
		Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
		System.out.println("twoYearsSixMonthsOneDay = " + twoYearsSixMonthsOneDay);
	}

	@Test
	void localdate(){
		LocalDate date1 = LocalDate.of(2017, 9, 21); // 2017-09-21
		System.out.println("date1 = " + date1);

		LocalDate date2 = date1.withYear(2022); // 2022-09-21
		System.out.println("date2 = " + date2);

		LocalDate date3 = date2.withDayOfMonth(2); // 2022-09-02
		System.out.println("date3 = " + date3);

		LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 2); // 2022-02-02
		System.out.println("date4 = " + date4);

		date1 = LocalDate.of(2017, 9, 21); // 2017-09-21
		System.out.println("date1 = " + date1);

		date2 = date1.plusWeeks(1); // 2017-09-28
		System.out.println("date2 = " + date2);

		date3 = date2.minusYears(1); // 2016-09-28
		System.out.println("date3 = " + date3);

		date4 = date3.plusWeeks(1); // 2016-10-05
		System.out.println("date4 = " + date4);
	}

	@Test
	void temporalAdjusters(){
		LocalDate date1 = LocalDate.of(2022, 2, 2); // 2022-02-02
		System.out.println("date1 = " + date1);

		LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)); // 2022-02-06
		System.out.println("date2 = " + date2);

		LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth()); // 2022-02-28
		System.out.println("date3 = " + date3);
	}

	class NextWorkingDay implements TemporalAdjuster{

		@Override
		public Temporal adjustInto(Temporal temporal) {
			DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			int dayToAdd = 1;
			if(dow == DayOfWeek.FRIDAY) dayToAdd = 3;
			else if(dow == DayOfWeek.SATURDAY) dayToAdd = 2;
			return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		}
	}

	@Test
	void custom_temporaladjuster(){
		LocalDate date1 = LocalDate.of(2022, 2, 4); // 2022-02-04
		System.out.println("date1 = " + date1);

		LocalDate date2 = date1.with(new NextWorkingDay()); // 2022-02-07
		System.out.println("date2 = " + date2);
	}

	@Test
	void dateTimeFormatter(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.of(2022, 02, 02);

		String formattedDate = date1.format(formatter);
		System.out.println("formattedDate = " + formattedDate);

		LocalDate date2 = LocalDate.parse(formattedDate, formatter);
		System.out.println("date2 = " + date2);
	}


	@Test
	void makeDateTimeFormatter(){
		DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
			.appendText(ChronoField.DAY_OF_MONTH)
			.appendLiteral(". ")
			.appendText(ChronoField.MONTH_OF_YEAR)
			.appendLiteral(" ")
			.appendText(ChronoField.YEAR)
			.parseCaseInsensitive()
			.toFormatter(Locale.ITALIAN);
		System.out.println("italianFormatter = " + italianFormatter);

		LocalDate date1 = LocalDate.of(2022, 02, 02);
		String formattedDate = date1.format(italianFormatter);
		System.out.println("formattedDate = " + formattedDate);
	}

	@Test
	void specificTime(){
		LocalDate date = LocalDate.of(2022, Month.FEBRUARY, 2);
		ZoneId romeZone = ZoneId.of("Europe/Rome");
		ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
		System.out.println("zdt1 = " + zdt1);

		LocalDateTime dateTime = LocalDateTime.of(2022, Month.FEBRUARY, 2, 13, 45);
		ZonedDateTime zdt2 = dateTime.atZone(romeZone);
		System.out.println("zdt2 = " + zdt2);

		Instant instant = Instant.now();
		ZonedDateTime zdt3 = instant.atZone(romeZone);
		System.out.println("zdt3 = " + zdt3);

		ZonedDateTime zdt4 = instant.atZone(ZoneId.of("Asia/Seoul"));
		System.out.println("zdt4 = " + zdt4);
	}




}
