package com.predicate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Function;

/**
 * Function to test if a date is a weekend
 */
public class IsLocalDateWeekendFunction implements Function<LocalDate, Boolean> {
    @Override
    public Boolean apply(LocalDate localDate) {
        return localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
