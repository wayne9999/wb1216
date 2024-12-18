import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class ToolRentalApp {
    private static final Map<String, Tool> tools = new HashMap<>();

    static {
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", BigDecimal.valueOf(1.49), true, false, true));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner", BigDecimal.valueOf(1.99), true, true, false));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", BigDecimal.valueOf(2.99), true, false, false));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", BigDecimal.valueOf(2.99), true, false, false));
    }

    public static void main(String[] args) {
        try {
            RentalAgreement agreement = checkout("LADW", 5, 10, LocalDate.of(2024, 9, 3));
            agreement.printAgreement();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental days must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }
        Tool tool = tools.get(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code.");
        }

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = calculateChargeDays(tool, checkoutDate, rentalDays);
        BigDecimal preDiscountCharge = tool.getDailyCharge().multiply(BigDecimal.valueOf(chargeDays));
        BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100));
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);

        return new RentalAgreement(tool, rentalDays, checkoutDate, discountPercent,
                preDiscountCharge.setScale(2, BigDecimal.ROUND_HALF_UP),
                discountAmount.setScale(2, BigDecimal.ROUND_HALF_UP),
                finalCharge.setScale(2, BigDecimal.ROUND_HALF_UP), dueDate);
    }

    private static int calculateChargeDays(Tool tool, LocalDate checkoutDate, int rentalDays) {
        int chargeDays = 0;
        LocalDate currentDate = checkoutDate.plusDays(1);
        for (int i = 0; i < rentalDays; i++) {
            boolean isWeekend = currentDate.getDayOfWeek() == java.time.DayOfWeek.SATURDAY ||
                    currentDate.getDayOfWeek() == java.time.DayOfWeek.SUNDAY;
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
