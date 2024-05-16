package Model;

import java.time.LocalDateTime;

/**
 * This is a Bill record class that represents a bill in the system.
 * It contains the id of the bill, the id of the order associated with the bill, the total amount of the bill, and the date and time the bill was created.
 */
public record Bill(
        int id,
        int orderId,
        double totalAmount,
        LocalDateTime createdAt) {
}