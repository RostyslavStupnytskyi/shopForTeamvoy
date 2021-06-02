package stupnytskyi.rostyslav.shop.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class OrderCreationRequest {

    @NotNull(message = "Field itemId in order creation request cannot be null")
    private Long itemId;

    @NotNull(message = "Field quantity in order creation request cannot be null")
    @Positive(message = "Field quantity in order creation request must be positive")
    private Integer quantity;

}
