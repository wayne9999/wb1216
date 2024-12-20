package model;

import java.math.BigDecimal;

// model.Ladder tool, extending the abstract model.Tool class
public class Ladder extends Tool {
    public Ladder(String toolCode, String brand, BigDecimal dailyCharge) {
        super(toolCode, "model.Ladder", brand, dailyCharge);
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