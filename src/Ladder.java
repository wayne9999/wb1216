import java.math.BigDecimal;

// Ladder tool, extending the abstract Tool class
public class Ladder extends Tool {
    public Ladder(String toolCode, String brand, BigDecimal dailyCharge) {
        super(toolCode, "Ladder", brand, dailyCharge);
    }

    @Override
    public boolean isWeekdayCharge() {
        return true;
    }

    @Override
    public boolean isWeekendCharge() {
        return true;
    }

    @Override
    public boolean isHolidayCharge() {
        return false;
    }
}