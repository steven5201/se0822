package com;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PointOfSaleDemoTest {

    private final Path testPath = Path.of("", "src/test/resources/" + PointOfSaleDemo.class.getSimpleName());

    @Test
    void test1() {
        String toolCode = "JAKR";
        String checkOutDate = "09/03/15";
        String rentalDays = "5";
        String discount = "101";

        assertThrows(Exception.class, () -> {
            RentalAgreement.generate(toolCode, rentalDays, discount, checkOutDate);
        });
    }

    @Test
    void test2() throws IOException {
        String toolCode = "LADW";
        String checkOutDate = "07/02/20";
        String rentalDays = "3";
        String discount = "10";

        String expected = Files.readString(testPath.resolve("test2"));

        assertDoesNotThrow(() -> {
            String actual = RentalAgreement.makeAgreement(
                    RentalAgreement.generate(
                            toolCode,
                            rentalDays,
                            discount,
                            checkOutDate
                    )
            );

            assertEquals(expected, actual);
        });
    }

    @Test
    void test3() throws IOException {
        String toolCode = "CHNS";
        String checkOutDate = "07/02/15";
        String rentalDays = "5";
        String discount = "25";

        String expected = Files.readString(testPath.resolve("test3"));

        assertDoesNotThrow(() -> {
            String actual = RentalAgreement.makeAgreement(
                    RentalAgreement.generate(
                            toolCode,
                            rentalDays,
                            discount,
                            checkOutDate
                    )
            );

            assertEquals(expected, actual);
        });
    }

    @Test
    void test4() throws IOException {
        String toolCode = "JAKD";
        String checkOutDate = "09/03/15";
        String rentalDays = "6";
        String discount = "0";

        String expected = Files.readString(testPath.resolve("test4"));

        assertDoesNotThrow(() -> {
            String actual = RentalAgreement.makeAgreement(
                    RentalAgreement.generate(
                            toolCode,
                            rentalDays,
                            discount,
                            checkOutDate
                    )
            );

            assertEquals(expected, actual);
        });
    }

    @Test
    void test5() throws IOException {
        String toolCode = "JAKR";
        String checkOutDate = "07/02/15";
        String rentalDays = "9";
        String discount = "0";

        String expected = Files.readString(testPath.resolve("test5"));

        assertDoesNotThrow(() -> {
            String actual = RentalAgreement.makeAgreement(
                    RentalAgreement.generate(
                            toolCode,
                            rentalDays,
                            discount,
                            checkOutDate
                    )
            );

            assertEquals(expected, actual);
        });
    }

    @Test
    void test6() throws IOException {
        String toolCode = "JAKR";
        String checkOutDate = "07/02/20";
        String rentalDays = "4";
        String discount = "50";

        String expected = Files.readString(testPath.resolve("test6"));

        assertDoesNotThrow(() -> {
            String actual = RentalAgreement.makeAgreement(
                    RentalAgreement.generate(
                            toolCode,
                            rentalDays,
                            discount,
                            checkOutDate
                    )
            );

            assertEquals(expected, actual);
        });
    }
}