package com.predicate.validator;

import java.util.function.Predicate;

/**
 * Validator for the Discount Percent input
 */
public class DiscountPercentValidator implements Predicate<String> {
    public static final String ERROR_TEXT = "Discount Percent Invalid.";

    @Override
    public boolean test(String s) {
        try {
            int parsed = Integer.parseInt(s);
            return parsed >= 0 && parsed <= 100;
        } catch (Exception e) {
            return false;
        }
    }
}
