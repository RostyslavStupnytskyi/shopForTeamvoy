package stupnytskyi.rostyslav.shop.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ItemCreationRequest {

    @NotNull(message = "Field title in item creation request cannot be null")
    @NotBlank(message = "Field title in item creation request cannot be blank")
    private String title;

    @NotNull(message = "Field quantity in item creation request cannot be null")
    @Positive(message = "Field quantity in item creation request must be positive")
    private Integer quantity;

    @NotNull(message = "Field price in item creation request cannot be null")
    @Positive(message = "Field price in item creation request must be positive")
    private Double price;

    @NotNull(message = "Field maker in item creation request cannot be null")
    @NotBlank(message = "Field maker in item creation request cannot be blank")
    private String maker;


}
