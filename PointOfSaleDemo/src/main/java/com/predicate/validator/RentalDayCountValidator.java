package com.predicate.validator;

import java.util.function.Predicate;

/**
 * Validator for the Rental Day Count input
 */
public class RentalDayCountValidator implements Predicate<String> {
    public static final String ERROR_TEXT = "Rental Day Invalid.";

    @Override
    public boolean test(String s) {
        try {
            int parsed = Integer.parseInt(s);
            return parsed > 1;
        } catch (Exception e) {
            return false;
        }
    }
}
