import java.math.BigDecimal;

// Jackhammer tool, extending the abstract Tool class
public class Jackhammer extends Tool {
    public Jackhammer(String toolCode, String brand, BigDecimal dailyCharge) {
        super(toolCode, "Jackhammer", brand, dailyCharge);
    }

    @Override
    public boolean isWeekdayCharge() {
        return true;
    }

    @Override
    public boolean isWeekendCharge() {
        return false;
    }

    @Override
    public boolean isHolidayCharge() {
        return false;
    }
}