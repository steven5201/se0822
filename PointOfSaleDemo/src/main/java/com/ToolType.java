package com;

/**
 * Enum for different types of tools
 */
public enum ToolType {
    Ladder(1.99, true, true, false),
    Chainsaw(1.49, true, false, true),
    Jackhammer(2.99, true, false, false);

    private final double dailyCharge;
    private final boolean weekdayChargeInd;
    private final boolean weekendChargeInd;
    private final boolean holidayChargeInd;

    /**
     * A type of tool
     *
     * @param dailyCharge   The daily charge for this type
     * @param weekdayCharge Should this charge for weekdays
     * @param weekendCharge Should this charge for weekends
     * @param holidayCharge Should this charge for holidays
     */
    private ToolType(double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.dailyCharge = dailyCharge;

        weekdayChargeInd = weekdayCharge;
        weekendChargeInd = weekendCharge;
        holidayChargeInd = holidayCharge;
    }

    /**
     * Get the name of this tool type
     *
     * @return The enum name
     */
    public String getName() {
        return this.name();
    }

    /**
     * Get the daily rate charged for this tool type
     *
     * @return The tool types daily rate
     */
    public double getDailyCharge() {
        return dailyCharge;
    }

    /**
     * Get if this type charges for weekdays
     *
     * @return True if should charge, False otherwise
     */
    public boolean getWeekdayChargeInd() {
        return weekdayChargeInd;
    }

    /**
     * Get if this type charges for weekends
     *
     * @return True if should charge, False otherwise
     */
    public boolean getWeekendChargeInd() {
        return weekendChargeInd;
    }

    /**
     * Get if this type charges for holidays
     *
     * @return True if should charge, False otherwise
     */
    public boolean getHolidayChargeInd() {
        return holidayChargeInd;
    }
}
