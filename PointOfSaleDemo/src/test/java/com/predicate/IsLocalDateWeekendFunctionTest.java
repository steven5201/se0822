package com.predicate;

import com.predicate.IsLocalDateWeekendFunction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IsLocalDateWeekendFunctionTest {

    IsLocalDateWeekendFunction predicate = new IsLocalDateWeekendFunction();

    @Test
    void isWeekend() {
        LocalDate date = LocalDate.of(2022, 8, 13);
        assertTrue(predicate.apply(date));
    }

    @Test
    void isWeekday() {
        LocalDate date = LocalDate.of(2022, 8, 12);
        assertFalse(predicate.apply(date));
    }
}