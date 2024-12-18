import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a rental agreement.
 * Encapsulates the details of a tool rental transaction.
 */

public class RentalAgreement {
    private final Tool tool;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final int discountPercent;
    private final BigDecimal preDiscountCharge;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;
    private final LocalDate dueDate;

    /**
     * Constructor to initialize the rental agreement.
     *
     * @param tool             The code of the rented tool.
     * @param rentalDays       Total rental days.
     * @param checkoutDate     Rental start date.
     * @param dueDate          Rental end date.
     * @param preDiscountCharge Total charge before discount.
     * @param discountPercent  Discount percentage applied.
     * @param discountAmount   Total discount amount.
     * @param finalCharge      Final rental cost after discount.
     */
    public RentalAgreement(Tool tool, int rentalDays, LocalDate checkoutDate, int discountPercent,
                           BigDecimal preDiscountCharge, BigDecimal discountAmount, BigDecimal finalCharge, LocalDate dueDate) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.discountPercent = discountPercent;
        this.preDiscountCharge = preDiscountCharge;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
        this.dueDate = dueDate;
    }

    public void printAgreement() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(dateFormatter));
        System.out.println("Due date: " + dueDate.format(dateFormatter));
        System.out.println("Daily rental charge: $" + tool.getDailyCharge());
        System.out.println("Charge days: " + calculateChargeDays());
        System.out.println("Pre-discount charge: $" + preDiscountCharge);
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: $" + discountAmount);
        System.out.println("Final charge: $" + finalCharge);
    }

    private int calculateChargeDays() {
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
