package model;

import java.math.BigDecimal;

// model.Jackhammer tool, extending the abstract model.Tool class
public class Jackhammer extends Tool {
    public Jackhammer(String toolCode, String brand, BigDecimal dailyCharge) {
        super(toolCode, "model.Jackhammer", brand, dailyCharge);
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