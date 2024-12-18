// RentalService Class to handle checkout logic and manage tool rentals
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

/**
 * Handles tool rental operations including managing tool inventory,
 * processing checkout requests, and calculating rental agreements.
 */

public class RentalService {
    private final Map<String, Tool> tools = new HashMap<>(); // Map to store tool inventory by tool code

    /**
     * Constructor to initialize the tool inventory with predefined tools.
     */
    public RentalService() {
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", BigDecimal.valueOf(1.49), true, false, true));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner", BigDecimal.valueOf(1.99), true, true, false));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", BigDecimal.valueOf(2.99), true, false, false));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", BigDecimal.valueOf(2.99), true, false, false));
    }

    /**
     * Processes the checkout operation and generates a rental agreement.
     *
     * @param toolCode       The code of the tool being rented.
     * @param rentalDays     The number of rental days (must be >= 1).
     * @param discountPercent The discount percentage (0-100).
     * @param checkoutDate The checkout date in MM/dd/yy format.
     * @return A RentalAgreement object containing the rental details.
     * @throws IllegalArgumentException If inputs are invalid.
     */
    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        // Validate rental days
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental days must be 1 or greater.");
        }
        // Validate discount percentage
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }
        // Retrieve the tool from inventory
        Tool tool = tools.get(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code.");
        }

        // Parse the checkout date and Calculate the due date
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        // Calculate chargeable days based on tool charge rules
        int chargeDays = calculateChargeDays(tool, checkoutDate, rentalDays);
        // Calculate rental charges
        BigDecimal preDiscountCharge = tool.getDailyCharge().multiply(BigDecimal.valueOf(chargeDays));
        BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);

        // Generate and return the rental agreement
        return new RentalAgreement(tool, rentalDays, checkoutDate, discountPercent,
                preDiscountCharge.setScale(2, RoundingMode.HALF_UP),
                discountAmount.setScale(2, RoundingMode.HALF_UP),
                finalCharge.setScale(2, RoundingMode.HALF_UP), dueDate);
    }

    /**
     * Calculates the number of chargeable days within the rental period.
     *
     * @param tool         The tool being rented.
     * @param checkoutDate The date when the rental starts.
     * @param rentalDays      The date when the rental ends.
     * @return The number of days the tool is chargeable.
     */
    private int calculateChargeDays(Tool tool, LocalDate checkoutDate, int rentalDays) {
        int chargeDays = 0;
        LocalDate currentDate = checkoutDate.plusDays(1); // Start from the day after checkout
        // Iterate through each day in the rental period
        for (int i = 0; i < rentalDays; i++) {
            boolean isWeekend = currentDate.getDayOfWeek() == java.time.DayOfWeek.SATURDAY ||
                    currentDate.getDayOfWeek() == java.time.DayOfWeek.SUNDAY;
            // Check if the current day is chargeable based on tool rules
            if ((isWeekend && tool.isWeekendCharge()) ||
                    (!isWeekend && tool.isWeekdayCharge()) ||
                    (HolidayCalculator.isHoliday(currentDate) && tool.isHolidayCharge())) {
                chargeDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return chargeDays;
    }
}
