package com.predicate.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToolCodeValidatorTest {

    ToolCodeValidator validator = new ToolCodeValidator();

    @Test
    void properCode() {
        assertTrue(validator.test("CHNS"));
    }

    @Test
    void improperCode() {
        assertFalse(validator.test("ABCD"));
    }
}