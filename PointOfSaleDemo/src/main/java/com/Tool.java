package com;

/**
 * Class to track a tools information
 */
public class Tool {
    // Unique identifier for a tool instance
    private final String code;
    // The type of tool. The type also specifies the daily rental charge, and the days for which the daily rental charge applies.
    private final ToolType toolType;
    // The brand of the ladder, chain saw or jackhammer.
    private final String brand;

    public Tool(String code, ToolType toolType, String brand) {
        this.code = code;
        this.toolType = toolType;
        this.brand = brand;
    }

    /**
     * Get the map for the GUI table
     *
     * @return Array of each value to be mapped in GUI
     */
    public String[] getTableMapForGUI() {
        return new String[]{
                getCode(),
                getToolTypeName(),
                brand
        };
    }

    /**
     * Get the code associated with this tool
     *
     * @return The tools code
     */
    public String getCode() {
        return code;
    }

    /**
     * Get the brand associated with this tool
     *
     * @return The tools brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Get the name of the tool type associated with this tool
     *
     * @return The name of this tools type
     */
    public String getToolTypeName() {
        return toolType.getName();
    }

    /**
     * Get the daily rate charged for this tool
     *
     * @return The tools daily rate
     */
    public double getDailyCharge() {
        return toolType.getDailyCharge();
    }

    /**
     * Get if this tool charges for weekdays
     *
     * @return True if should charge, False otherwise
     */
    public boolean getWeekdayChargeInd() {
        return toolType.getWeekdayChargeInd();
    }

    /**
     * Get if this tool charges for weekends
     *
     * @return True if should charge, False otherwise
     */
    public boolean getWeekendChargeInd() {
        return toolType.getWeekendChargeInd();
    }

    /**
     * Get if this tool charges for holidays
     *
     * @return True if should charge, False otherwise
     */
    public boolean getHolidayChargeInd() {
        return toolType.getHolidayChargeInd();
    }
}
