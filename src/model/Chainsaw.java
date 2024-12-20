package model;

import java.math.BigDecimal;

// model.Chainsaw tool, extending the abstract model.Tool class
public class Chainsaw extends Tool {
    public Chainsaw(String toolCode, String brand, BigDecimal dailyCharge) {
        super(toolCode, "model.Chainsaw", brand, dailyCharge);
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