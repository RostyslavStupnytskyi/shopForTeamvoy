package stupnytskyi.rostyslav.shop.exceptions;

public class OrderCannotBeDeletedException extends Exception {
    public OrderCannotBeDeletedException(Long orderId) {
        super("Order with id " + orderId + " cannot be deleted. Order time is not expired");
    }
}
