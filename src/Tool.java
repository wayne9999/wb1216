import java.math.BigDecimal;

/**
 * Represents a tool available for rental.
 * Encapsulates the attributes of a tool including its rental charges and applicable charge rules.
 */
public class Tool {
    // Unique identifier for the tool (e.g., CHNS, LADW)
    private final String toolCode;
    // Type of tool (e.g., Chainsaw, Ladder, Jackhammer)
    private final String toolType;
    // Brand of the tool (e.g., Stihl, Werner, DeWalt)
    private final String brand;
    // Daily rental charge for the tool
    private final BigDecimal dailyCharge;
    // Indicates if charges apply on weekdays
    private final boolean weekdayCharge;
    // Indicates if charges apply on weekends
    private final boolean weekendCharge;
    // Indicates if charges apply on holidays
    private final boolean holidayCharge;

    /**
     * Constructor to initialize the attributes of the Tool class.
     *
     * @param toolCode      The unique identifier for the tool.
     * @param toolType      The type of tool being rented.
     * @param brand         The brand of the tool.
     * @param dailyCharge   The daily rental charge for the tool.
     * @param weekdayCharge Whether the tool charges apply on weekdays.
     * @param weekendCharge Whether the tool charges apply on weekends.
     * @param holidayCharge Whether the tool charges apply on holidays.
     */
    public Tool(String toolCode, String toolType, String brand, BigDecimal dailyCharge,
                boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

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

    // Returns true if charges apply on weekdays
    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    // Returns true if charges apply on weekends
    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    // Returns true if charges apply on holidays
    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}
