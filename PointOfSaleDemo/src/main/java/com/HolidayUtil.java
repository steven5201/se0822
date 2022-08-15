package com;

import com.exception.BadDateRangeException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility to handle holidays
 */
public class HolidayUtil {
    private static final Logger logger = Logger.getLogger(
            HolidayUtil.class.getPackage().getName() +
                    "." + HolidayUtil.class.getName());

    private static Map<Integer, LocalDate> independenceDayMap = new HashMap();
    private static Map<Integer, LocalDate> laborDayMap = new HashMap();

    /**
     * Get the independence day holiday
     * <p>
     * July 4th
     * If on Saturday then date is moved to Friday
     * If on Sunday then date is moved to Monday
     *
     * @param year The year to get the holiday for
     * @return LocalDate that the holiday lands on
     */
    public static LocalDate getIndependenceDayForYear(int year) {
        return independenceDayMap.computeIfAbsent(year, y -> {
            LocalDate holidayDay = LocalDate.of(y, 7, 4);

            switch (holidayDay.getDayOfWeek()) {
                case SATURDAY:
                    holidayDay = holidayDay.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
                    break;
                case SUNDAY:
                    holidayDay = holidayDay.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                    break;
            }

            logger.log(Level.INFO, "New IndependenceDay value calculated for year " + y + " as " + holidayDay);
            return holidayDay;
        });
    }

    /**
     * Get the labor day holiday
     * <p>
     * Always on the first Monday of September
     *
     * @param year The year to get the holiday for
     * @return LocalDate that the holiday lands on
     */
    public static LocalDate getLaborDayForYear(int year) {
        return laborDayMap.computeIfAbsent(year, y -> {
            LocalDate holidayDay = LocalDate.of(y, 9, 1)
                    .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

            logger.log(Level.INFO, "New LaborDay value calculated for year " + y + " as " + holidayDay);
            return holidayDay;
        });
    }

    /**
     * Gets the number of holidays in a given date range
     *
     * @param startDate The start of the date range
     * @param endDate   The end of the date range
     * @return int for the number of holidays
     * @throws BadDateRangeException When end date is before start date
     */
    public static int getHolidayCountForDateRange(LocalDate startDate, LocalDate endDate) throws BadDateRangeException {
        if (endDate.isBefore(startDate)) {
            throw new BadDateRangeException(startDate, endDate);
        }

        return (int) startDate
                .datesUntil(endDate, Period.ofYears(1)) // Get each year between start and end
                .flatMap(date -> Arrays.stream(getAllHolidaysForYear(date.getYear()))) // Get all holidays in range
                .filter(holiday -> startDate.isBefore(holiday) && endDate.isAfter(holiday)) // Filter holidays outside range
                .count(); // All dates still in list must be within the range
    }

    /**
     * Gets all the holidays for a given year
     *
     * @param year year to get holidays for
     * @return List of holidays in year
     */
    private static LocalDate[] getAllHolidaysForYear(int year) {
        return new LocalDate[]{
                getIndependenceDayForYear(year),
                getLaborDayForYear(year)
        };
    }
}
