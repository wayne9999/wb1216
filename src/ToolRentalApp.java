import java.time.LocalDate;
import java.util.Map;

/**
 * Main entry point for the application.
 * Demonstrates tool rental functionality and showcases the use of core classes.
 */

public class ToolRentalApp {

    public static void main(String[] args) {
        // Seed data using the ToolSeeder
        Map<String, Tool> tools = ToolSeeder.getToolInventory();

        // Inject the seeded tool inventory into the RentalService
        IRentalService rentalService = new RentalService(tools);

        try {
            // Example input for a rental checkout
            String toolCode = "LADW";        // Tool code for Ladder
            int rentalDays = 5;             // Number of rental days
            int discountPercent = 10;       // Discount percentage
            LocalDate checkoutDate = LocalDate.of(2024, 9, 3); // Checkout date in MM/dd/yy format

            // Perform checkout and generate the rental agreement
            RentalAgreement rentalAgreement = rentalService.checkout(toolCode, rentalDays, discountPercent, checkoutDate);

            // Print the rental agreement details
            System.out.println("Rental Agreement:");
            rentalAgreement.printAgreement();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


}
