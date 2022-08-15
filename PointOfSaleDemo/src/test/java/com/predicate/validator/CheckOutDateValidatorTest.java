package com.predicate.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckOutDateValidatorTest {

    CheckOutDateValidator validator = new CheckOutDateValidator();

    @Test
    void goodDate() {
        assertTrue(validator.test("08/13/2022"));
    }

    @Test
    void badDate() {
        assertFalse(validator.test("2022/13/08"));
    }
}