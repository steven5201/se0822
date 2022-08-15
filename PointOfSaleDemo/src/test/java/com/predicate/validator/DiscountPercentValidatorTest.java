package com.predicate.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountPercentValidatorTest {

    DiscountPercentValidator validator = new DiscountPercentValidator();

    @Test
    void discountPercentOver100() {
        assertFalse(validator.test("101"));
    }

    @Test
    void discountPercentUnder0() {
        assertFalse(validator.test("-10"));
    }

    @Test
    void discountPercentCorrect() {
        assertTrue(validator.test("50"));
        assertTrue(validator.test("0"));
        assertTrue(validator.test("100"));
    }
}