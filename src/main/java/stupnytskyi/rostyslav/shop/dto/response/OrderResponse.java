package stupnytskyi.rostyslav.shop.dto.response;

import lombok.Getter;
import lombok.Setter;
import stupnytskyi.rostyslav.shop.entity.Order;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private Double price;
    private Integer quantity;
    private boolean isValid;
    private ItemResponse item;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.price = order.getPrice();
        this.quantity = order.getQuantity();
        this.isValid = order.isValid();
        this.item = new ItemResponse(order.getItem());
    }

    public OrderResponse() { // no args constructor for parse from JSON
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", isValid=" + isValid +
                ", item=" + item +
                '}';
    }
}
