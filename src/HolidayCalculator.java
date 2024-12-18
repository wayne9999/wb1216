import java.time.LocalDate;

public class HolidayCalculator {
    public static boolean isHoliday(LocalDate date) {
        int year = date.getYear();
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        LocalDate laborDay = LocalDate.of(year, 9, 1).with(java.time.temporal.TemporalAdjusters.firstInMonth(java.time.DayOfWeek.MONDAY));

        if (independenceDay.getDayOfWeek() == java.time.DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == java.time.DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        return date.equals(independenceDay) || date.equals(laborDay);
    }
}