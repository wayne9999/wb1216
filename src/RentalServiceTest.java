import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RentalServiceTest {

    private IRentalService rentalService; // Use the interface for flexibility

    /**
     * Setup method to initialize the tools and RentalService before each test.
     * This ensures that the tests work with a fresh, valid inventory.
     */
    @BeforeEach
    void setUp() {
        // The map holding the tool inventory
        Map<String, Tool> tools = new HashMap<>();
        tools.put("CHNS", new Chainsaw("CHNS", "Stihl", BigDecimal.valueOf(1.49)));
        tools.put("LADW", new Ladder("LADW", "Werner", BigDecimal.valueOf(1.99)));
        tools.put("JAKD", new Jackhammer("JAKD", "DeWalt", BigDecimal.valueOf(2.99)));
        tools.put("JAKR", new Jackhammer("JAKR", "Ridgid", BigDecimal.valueOf(2.99)));
        rentalService = new RentalService(tools); // Inject the tools map into RentalService
    }

    /**
     * Test that an invalid tool code throws an IllegalArgumentException.
     */
    @Test
    void testCheckoutInvalidToolCode() {
        LocalDate checkoutDate = LocalDate.of(2024, 9, 3); // LocalDate input
        Exception exception = assertThrows(IllegalArgumentException.class, () -> rentalService.checkout("INVALID", 5, 10, checkoutDate));
        assertEquals("Tool not found: INVALID", exception.getMessage());
    }

    /**
     * Test that an invalid discount percentage throws an IllegalArgumentException.
     */
    @Test
    void testCheckoutInvalidDiscountPercent() {
        LocalDate checkoutDate = LocalDate.of(2024, 9, 3); // LocalDate input
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalService.checkout("LADW", 5, 110, checkoutDate); // Discount exceeds 100%
        });
        assertEquals("Discount percent must be between 0 and 100.", exception.getMessage());
    }

    /**
     * Test that an invalid rental day count throws an IllegalArgumentException.
     */
    @Test
    void testCheckoutInvalidRentalDays() {
        LocalDate checkoutDate = LocalDate.of(2024, 9, 3); // LocalDate input
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalService.checkout("LADW", 0, 10, checkoutDate); // Rental days less than 1
        });
        assertEquals("Rental days must be at least 1.", exception.getMessage());
    }

    /**
     * Test that an invalid date format throws an IllegalArgumentException.
     */
    @Test
    void testCheckoutInvalidDateFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalService.checkout("LADW", 5, 10, null); // Invalid date format
        });
        assertEquals("Invalid date format. Use MM/dd/yy.", exception.getMessage());
    }

    /**
     * Test null inputs for tool code or checkout date.
     */
    @Test
    void testNullInputs() {
        LocalDate checkoutDate = LocalDate.of(2024, 9, 3); // LocalDate input
        Exception exception = assertThrows(IllegalArgumentException.class, () -> rentalService.checkout(null, 5, 10, checkoutDate));
        assertEquals("Tool code and checkout date cannot be null.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> rentalService.checkout("LADW", 5, 10, null));
        assertEquals("Tool code and checkout date cannot be null.", exception.getMessage());
    }
}
