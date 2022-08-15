package com;

import com.exception.BadDateRangeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HolidayUtilTest {

    @Test
    void getMondayIndependenceDay() {
        final int year = 2022;
        final LocalDate expected = LocalDate.of(year, 7, 4);

        final LocalDate actual = HolidayUtil.getIndependenceDayForYear(year);
        assertEquals(expected, actual);
    }

    @Test
    void getWednesdayIndependenceDay() {
        final int year = 2018;
        final LocalDate expected = LocalDate.of(year, 7, 4);

        final LocalDate actual = HolidayUtil.getIndependenceDayForYear(year);
        assertEquals(expected, actual);
    }

    @Test
    void getFridayIndependenceDay() {
        final int year = 2019;
        final LocalDate expected = LocalDate.of(year, 7, 4);

        final LocalDate actual = HolidayUtil.getIndependenceDayForYear(year);
        assertEquals(expected, actual);
    }

    @Test
    void getSaturdayIndependenceDay() {
        final int year = 2020;
        final LocalDate expected = LocalDate.of(year, 7, 3);

        final LocalDate actual = HolidayUtil.getIndependenceDayForYear(year);
        assertEquals(expected, actual);
    }

    @Test
    void getSundayIndependenceDay() {
        final int year = 2021;
        final LocalDate expected = LocalDate.of(year, 7, 5);

        final LocalDate actual = HolidayUtil.getIndependenceDayForYear(year);
        assertEquals(expected, actual);
    }

    @Test
    void getLaborDayForYear() {
        final int year = 2022;
        final LocalDate expected = LocalDate.of(year, 9, 5);

        final LocalDate actual = HolidayUtil.getLaborDayForYear(year);
        assertEquals(expected, actual);
    }

    @Test
    void getLaborDayOnFirst() {
        final int year = 2014;
        final LocalDate expected = LocalDate.of(year, 9, 1);

        final LocalDate actual = HolidayUtil.getLaborDayForYear(year);
        assertEquals(expected, actual);
    }

    @Test
    void getHolidayCountForDateRange() {
        final LocalDate startDate = LocalDate.of(2022, 5, 20);
        final LocalDate endDate = LocalDate.of(2022, 10, 22);
        final int expected = 2;

        assertDoesNotThrow(() -> {
            final int actual = HolidayUtil.getHolidayCountForDateRange(startDate, endDate);
            assertEquals(expected, actual);
        });
    }

    @Test
    void getHolidayCountForDateRangeMultiYear() {
        final LocalDate startDate = LocalDate.of(2020, 5, 20);
        final LocalDate endDate = LocalDate.of(2022, 10, 22);
        final int expected = 6;

        assertDoesNotThrow(() -> {
            final int actual = HolidayUtil.getHolidayCountForDateRange(startDate, endDate);
            assertEquals(expected, actual);
        });
    }

    @Test
    void getHolidayCountForDateRangeBackward() {
        final LocalDate startDate = LocalDate.of(2022, 10, 20);
        final LocalDate endDate = LocalDate.of(2022, 5, 22);

        assertThrows(BadDateRangeException.class, () -> {
            HolidayUtil.getHolidayCountForDateRange(startDate, endDate);
        });
    }
}