import java.math.BigDecimal;

// Chainsaw tool, extending the abstract Tool class
public class Chainsaw extends Tool {
    public Chainsaw(String toolCode, String brand, BigDecimal dailyCharge) {
        super(toolCode, "Chainsaw", brand, dailyCharge);
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
        return true;
    }
}