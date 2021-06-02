package stupnytskyi.rostyslav.shop.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ItemUpdateQuantityRequest {

    @NotNull(message = "Field itemId in item update quantity request cannot be null")
    private Long itemId;

    @NotNull(message = "Field quantity in item update quantity request cannot be null")
    @Positive(message = "Field quantity in item update quantity request must be positive")
    private Integer quantity;
}
