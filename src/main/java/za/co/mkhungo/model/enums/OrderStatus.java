package za.co.mkhungo.model.enums;

/**
 * @author Noxolo.Mkhungo
 *
 * Enum representing the status of an order.
 */
public enum OrderStatus {
    ACCEPTED, PACKAGED, SHIPPED,
    DELIVERED, RETURNED, CANCELLED,
    PENDING, PROCESSING, COMPLETED, FAILED
}
