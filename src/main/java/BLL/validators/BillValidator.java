package BLL.validators;

import java.time.LocalDateTime;

/**
 * This is a BillValidator class that validates the total amount and creation date of a bill.
 */
public class BillValidator {
    private static final double MIN_AMOUNT = 0;

    /**
     * This method validates the total amount and creation date of a bill.
     * @param totalAmount The total amount of the bill.
     * @param createdAt The creation date of the bill.
     * @throws IllegalArgumentException if the total amount is less than or equal to MIN_AMOUNT or if the creation date is in the future.
     */
    public static void validate(double totalAmount, LocalDateTime createdAt) {
        if (totalAmount <= MIN_AMOUNT) {
            throw new IllegalArgumentException("Total amount must be greater than " + MIN_AMOUNT);
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (createdAt.isAfter(currentDateTime)) {
            throw new IllegalArgumentException("Date cannot be in the future!");
        }
    }
}
