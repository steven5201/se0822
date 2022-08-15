package com.exception;

import java.time.LocalDate;

/**
 * Bad date range exception
 */
public class BadDateRangeException extends Exception {

    public BadDateRangeException(LocalDate startDate, LocalDate endDate) {
        super("Start Date (" + startDate.toString() + ") must be before End Date (" + endDate.toString() + ")");
    }
}
