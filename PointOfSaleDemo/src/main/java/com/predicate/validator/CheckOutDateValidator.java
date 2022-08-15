package com.predicate.validator;

import com.gui.GuiUtil;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

/**
 * Validator for the Check Out Date input
 */
public class CheckOutDateValidator implements Predicate<String> {

    public static final String ERROR_TEXT = "Check Out Date Invalid.";
    private final SimpleDateFormat simpleDateFormat;

    public CheckOutDateValidator() {
        simpleDateFormat = new SimpleDateFormat(GuiUtil.Constants.DATE_FORMAT);
        simpleDateFormat.setLenient(false);
    }

    @Override
    public boolean test(String s) {
        try {
            simpleDateFormat.parse(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
