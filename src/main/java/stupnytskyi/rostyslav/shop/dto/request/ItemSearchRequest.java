package stupnytskyi.rostyslav.shop.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter

public class ItemSearchRequest {

    @NotNull(message = "Field title in search request cannot be null")
    @NotBlank(message = "Field title in search request cannot be blank")
    private String title;

    @NotNull(message = "Field quantity in search request cannot be null")
    @Positive(message = "Field quantity in search request must be positive")
    private Integer quantity;

    public ItemSearchRequest(String title, Integer quantity) {
        this.title = title;
        this.quantity = quantity;
    }
}
