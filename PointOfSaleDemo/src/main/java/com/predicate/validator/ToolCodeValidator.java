package com.predicate.validator;

import com.PointOfSaleDemo;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Validator for the Tool Code input
 */
public class ToolCodeValidator implements Predicate<String> {
    public static final String ERROR_TEXT = "Tool Code Invalid.";

    @Override
    public boolean test(String s) {
        return Arrays.stream(PointOfSaleDemo.TOOLS).anyMatch(tool -> tool.getCode().equals(s));
    }
}
