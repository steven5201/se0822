package com;

import com.exception.BadDateRangeException;
import com.gui.GuiUtil;
import com.predicate.IsLocalDateWeekendFunction;
import com.predicate.validator.CheckOutDateValidator;
import com.predicate.validator.DiscountPercentValidator;
import com.predicate.validator.RentalDayCountValidator;
import com.predicate.validator.ToolCodeValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A rental agreement
 */
public class RentalAgreement {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(GuiUtil.Constants.DATE_FORMAT);
    private static final DecimalFormat CURRENCY_FORMATTER = new DecimalFormat("$#.##");

    private static final ToolCodeValidator TOOL_CODE_VALIDATOR = new ToolCodeValidator();
    private static final RentalDayCountValidator RENTAL_DAY_COUNT_VALIDATOR = new RentalDayCountValidator();
    private static final DiscountPercentValidator DISCOUNT_PERCENT_VALIDATOR = new DiscountPercentValidator();
    private static final CheckOutDateValidator CHECK_OUT_DATE_VALIDATOR = new CheckOutDateValidator();

    /**
     * Generate the rental agreement with pre-validated results
     *
     * @param toolCode        The tool code to generate report for
     * @param rentalDayCount  The number of days to rent tool
     * @param discountPercent The percent of discount
     * @param checkOutDate    The start date for the checkout
     * @return LinkedHashMap with label and value pairs
     * @throws BadDateRangeException When end date is before start date
     */
    public static LinkedHashMap<String, Object> generate(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkOutDate) throws BadDateRangeException {
        final LinkedHashMap report = new LinkedHashMap<String, Object>();

        final Tool tool = Arrays.stream(PointOfSaleDemo.TOOLS)
                .filter(t -> t.getCode().equals(toolCode))
                .findFirst()
                .get();
        final LocalDate dueDate = checkOutDate.plus(rentalDayCount, ChronoUnit.DAYS);

        final Map<Boolean, List<LocalDate>> groupedDatesBetween = checkOutDate.datesUntil(dueDate)
                .collect(Collectors.groupingBy(new IsLocalDateWeekendFunction()));

        final int weekdayCount = tool.getWeekdayChargeInd() ? groupedDatesBetween.get(false).size() : 0;
        final int weekendCount = tool.getWeekendChargeInd() ? groupedDatesBetween.get(true).size() : 0;
        final int holidayCount = tool.getHolidayChargeInd() ? 0 : HolidayUtil.getHolidayCountForDateRange(checkOutDate, dueDate);

        final int finalChargedDays = weekdayCount + weekendCount - holidayCount;

        final BigDecimal chargePreDiscount = new BigDecimal(finalChargedDays)
                .multiply(new BigDecimal(tool.getDailyCharge()))
                .setScale(2, RoundingMode.HALF_UP);

        final BigDecimal discountAmount = chargePreDiscount
                .multiply(new BigDecimal(discountPercent))
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);

        report.put("Tool Code", toolCode);
        report.put("Tool Type", tool.getToolTypeName());
        report.put("Tool Brand", tool.getBrand());
        report.put("Rental Days", rentalDayCount);
        report.put("Check Out Date", checkOutDate.format(DATE_TIME_FORMATTER));
        report.put("Due Date", dueDate.format(DATE_TIME_FORMATTER));
        report.put("Daily Rental Charge", CURRENCY_FORMATTER.format(tool.getDailyCharge()));
        report.put("Charge Days", finalChargedDays);
        report.put("Pre-Discount Charge", CURRENCY_FORMATTER.format(chargePreDiscount));
        report.put("Discount Percent", discountPercent + "%");
        report.put("Discount Amount", CURRENCY_FORMATTER.format(discountAmount));
        report.put("Final Charge", CURRENCY_FORMATTER.format(chargePreDiscount.subtract(discountAmount)));

        return report;
    }

    /**
     * Validate inputs and generate the rental agreement
     *
     * @param toolCode        The tool code to generate report for
     * @param rentalDayCount  The number of days to rent tool
     * @param discountPercent The percent of discount
     * @param checkOutDate    The start date for the checkout
     * @return LinkedHashMap with label and value pairs
     * @throws Exception When any validation fails
     */
    public static LinkedHashMap<String, Object> generate(String toolCode, String rentalDayCount, String discountPercent, String checkOutDate) throws Exception {
        if (!TOOL_CODE_VALIDATOR.test(toolCode)) {
            throw new Exception(ToolCodeValidator.ERROR_TEXT);
        }
        if (!RENTAL_DAY_COUNT_VALIDATOR.test(rentalDayCount)) {
            throw new Exception(RentalDayCountValidator.ERROR_TEXT);
        }
        if (!DISCOUNT_PERCENT_VALIDATOR.test(discountPercent)) {
            throw new Exception(DiscountPercentValidator.ERROR_TEXT);
        }
        if (!CHECK_OUT_DATE_VALIDATOR.test(checkOutDate)) {
            throw new Exception(CheckOutDateValidator.ERROR_TEXT);
        }

        return RentalAgreement.generate(toolCode,
                Integer.parseInt(rentalDayCount),
                Integer.parseInt(discountPercent),
                LocalDate.parse(checkOutDate, DATE_TIME_FORMATTER)
        );
    }

    /**
     * Make a user displayable agreement using a generated map
     *
     * @param generatedRentalAgreementMap The map to make the agreement with
     * @return The user displayable agreement
     */
    public static String makeAgreement(LinkedHashMap<String, Object> generatedRentalAgreementMap) {
        StringBuilder rentalAgreement = new StringBuilder();

        generatedRentalAgreementMap.forEach((label, value) -> {
            rentalAgreement.append(label);
            rentalAgreement.append(": ");
            rentalAgreement.append(value);
            rentalAgreement.append("\r\n");
        });

        return rentalAgreement.toString();
    }
}
