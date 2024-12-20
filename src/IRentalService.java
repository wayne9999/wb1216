import java.time.LocalDate;

// Interface for Rental Service
public interface IRentalService {
    RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate);
}