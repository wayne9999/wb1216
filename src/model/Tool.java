package model;

import java.math.BigDecimal;

/**
 * Represents a tool available for rental.
 * Encapsulates the attributes of a tool including its rental charges and applicable charge rules.
 */
public abstract class Tool {
    // Unique identifier for the tool (e.g., CHNS, LADW)
    private final String toolCode;
    // Type of tool (e.g., model.Chainsaw, model.Ladder, model.Jackhammer)
    private final String toolType;
    // Brand of the tool (e.g., Stihl, Werner, DeWalt)
    private final String brand;
    // Daily rental charge for the tool
    private final BigDecimal dailyCharge;

    /**
     * Constructor to initialize the attributes of the model.Tool class.
     *
     * @param toolCode    The unique identifier for the tool.
     * @param toolType    The type of tool being rented.
     * @param brand       The brand of the tool.
     * @param dailyCharge The daily rental charge for the tool.
     */
    public Tool(String toolCode, String toolType, String brand, BigDecimal dailyCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
    }

    // Abstract methods for charges, to be implemented by subclasses
    public abstract boolean isWeekdayCharge();

    public abstract boolean isWeekendCharge();

    public abstract boolean isHolidayCharge();

    // Getter for tool code
    public String getToolCode() {
        return toolCode;
    }

    // Getter for tool type
    public String getToolType() {
        return toolType;
    }

    // Getter for tool brand
    public String getBrand() {
        return brand;
    }

    // Getter for daily charge
    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }
}
