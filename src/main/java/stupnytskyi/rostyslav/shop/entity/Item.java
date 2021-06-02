package stupnytskyi.rostyslav.shop.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Price of item cannot be null")
    @Positive(message = "Price of item must be positive number")
    private Double price;

    @NotNull(message = "Quantity of item cannot be null")
    @Positive(message = "Quantity of item must be positive number")
    private Integer availableQuantity;

    @NotNull(message = "Title of item cannot be null")
    @NotBlank(message = "Title of item cannot be blank")
    private String title;

    @NotNull(message = "Maker of item cannot be null")
    @NotBlank(message = "Maker of item cannot be blank")
    private String maker;

    public boolean isQuantityEnable(Integer quantity) {
        return this.availableQuantity >= quantity;
    }

}
