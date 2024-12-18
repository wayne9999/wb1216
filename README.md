# Tool Rental Application

## Overview

This project is a simple tool rental application implemented in Java. It demonstrates core software engineering principles such as object-oriented design, encapsulation, and robust error handling. The application allows customers to rent tools, calculates rental agreements, and prints them in a user-friendly format.

## Key Features

- **Tool Management**: Provides predefined tools with unique codes, brands, and rental charge rules.
- **Holiday Calculation**: Accounts for specific holidays (Independence Day, Labor Day) and adjusts charge days accordingly.
- **Rental Agreement**: Generates detailed agreements including charges, discounts, and due dates.
- **JDK Compliance**: Built using modern Java standards (JDK 9+).
- **No External Dependencies**: Uses Java's standard library only.

## Technologies Used

- **Language**: Java (JDK 23 compatible)
- **IDE**: IntelliJ IDEA (recommended for smooth execution)

## Setup and Usage

### Prerequisites

- Java JDK 17 or above.
- IntelliJ IDEA or any IDE supporting modern Java.

### Running the Application

1. Clone this repository:
   ```bash
   git clone <repository-url>
   ```
2. Open the project in IntelliJ IDEA.
3. Navigate to the `ToolRentalApp` class.
4. Run the `main` method to execute the application.

### Example Output

```
Selected Tool Details:
Tool Code: LADW
Tool Type: Ladder
Brand: Werner
Daily Charge: $1.99
Is 2024-07-04 a holiday? true
Tool code: LADW
Tool type: Ladder
Tool brand: Werner
Rental days: 5
Check out date: 09/03/24
Due date: 09/08/24
Daily rental charge: $1.99
Charge days: 3
Pre-discount charge: $5.97
Discount percent: 10%
Discount amount: $0.60
Final charge: $5.37
```

## Project Structure

### Classes

#### `Tool`

Represents a rental tool with attributes:

- Tool code
- Tool type
- Brand
- Charges (weekday, weekend, holiday)

#### `HolidayCalculator`

Utility class to determine if a date is a holiday based on:

- Independence Day (adjusted for weekends)
- Labor Day (first Monday of September)

#### `RentalAgreement`

Encapsulates details of a rental transaction including:

- Tool details
- Rental period
- Charges and discounts

#### `ToolRentalApp`

Main entry point to demonstrate the application's functionality:

- Predefined tools.
- Checkout logic.
- Rental agreement generation and printing.

### Key Methods

#### `checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate)`

Validates inputs, calculates charges, and generates a `RentalAgreement`.

#### `HolidayCalculator.isHoliday(LocalDate date)`

Determines if a given date is a holiday.

#### `RentalAgreement.printAgreement()`

Prints the details of the rental agreement in a user-friendly format.


