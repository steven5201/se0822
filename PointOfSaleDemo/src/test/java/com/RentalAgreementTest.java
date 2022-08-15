package com;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RentalAgreementTest {

    private final Path testPath = Path.of("", "src/test/resources/" + RentalAgreement.class.getSimpleName());

    @Test
    void generateValidateAllNull() {
        assertThrows(Exception.class, () -> {
            RentalAgreement.generate(null, null, null, null);
        });
    }

    @Test
    void generateValidateBadToolCode() {
        assertThrows(Exception.class, () -> {
            RentalAgreement.generate("ABCD", "5", "50", "08/15/22");
        });
    }

    @Test
    void generateValidateBadRentalDayCount() {
        assertThrows(Exception.class, () -> {
            RentalAgreement.generate("JAKR", "-5", "50", "08/15/22");
        });
    }

    @Test
    void generateValidateBadDiscountPercent() {
        assertThrows(Exception.class, () -> {
            RentalAgreement.generate("JAKR", "5", "-50", "08/15/22");
        });
    }

    @Test
    void generateValidateBadCheckOutDate() {
        assertThrows(Exception.class, () -> {
            RentalAgreement.generate("JAKR", "5", "50", "82/15/02");
        });
    }

    @Test
    void generateValidateMap() {
        assertDoesNotThrow(() -> {
            LinkedHashMap actual = RentalAgreement.generate("JAKR", "5", "50", "08/15/22");

            LinkedHashMap expected = new LinkedHashMap<String, Object>();
            expected.put("Tool Code", "JAKR");
            expected.put("Tool Type", "Jackhammer");
            expected.put("Tool Brand", "Ridgid");
            expected.put("Rental Days", 5);
            expected.put("Check Out Date", "08/15/22");
            expected.put("Due Date", "08/20/22");
            expected.put("Daily Rental Charge", "$2.99");
            expected.put("Charge Days", 5);
            expected.put("Pre-Discount Charge", "$14.95");
            expected.put("Discount Percent", "50%");
            expected.put("Discount Amount", "$7.48");
            expected.put("Final Charge", "$7.47");

            assertArrayEquals(actual.entrySet().toArray(), expected.entrySet().toArray());
            assertArrayEquals(actual.values().toArray(), expected.values().toArray());
        });
    }

    @Test
    void generatePreValidatedMap() {
        assertDoesNotThrow(() -> {
            LinkedHashMap actual = RentalAgreement.generate("JAKR",
                    5,
                    50,
                    LocalDate.of(2022, 8, 15)
            );

            LinkedHashMap expected = new LinkedHashMap<String, Object>();
            expected.put("Tool Code", "JAKR");
            expected.put("Tool Type", "Jackhammer");
            expected.put("Tool Brand", "Ridgid");
            expected.put("Rental Days", 5);
            expected.put("Check Out Date", "08/15/22");
            expected.put("Due Date", "08/20/22");
            expected.put("Daily Rental Charge", "$2.99");
            expected.put("Charge Days", 5);
            expected.put("Pre-Discount Charge", "$14.95");
            expected.put("Discount Percent", "50%");
            expected.put("Discount Amount", "$7.48");
            expected.put("Final Charge", "$7.47");

            assertArrayEquals(actual.entrySet().toArray(), expected.entrySet().toArray());
            assertArrayEquals(actual.values().toArray(), expected.values().toArray());
        });
    }

    @Test
    void makeAgreement() {
        assertDoesNotThrow(() -> {
            String expected = Files.readString(testPath.resolve("test"));

            String actual = RentalAgreement.makeAgreement(
                    RentalAgreement.generate("JAKR",
                            5,
                            50,
                            LocalDate.of(2022, 8, 15)
                    )
            );

            assertEquals(expected, actual);
        });
    }
}