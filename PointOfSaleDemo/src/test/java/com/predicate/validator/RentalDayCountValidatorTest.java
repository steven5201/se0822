package com.predicate.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalDayCountValidatorTest {

    RentalDayCountValidator validator = new RentalDayCountValidator();

    @Test
    void rentalDayWrong() {
        assertFalse(validator.test("-10"));
        assertFalse(validator.test("0"));
        assertFalse(validator.test("1"));
    }

    @Test
    void rentalDayCorrect() {
        assertTrue(validator.test("50"));
        assertTrue(validator.test("2"));
        assertTrue(validator.test("100"));
    }
}