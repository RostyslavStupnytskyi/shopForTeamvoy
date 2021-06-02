package stupnytskyi.rostyslav.shop.entity;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Price of order cannot be null")
    @Positive(message = "Price of order must be positive number")
    private Double price;

    @NotNull(message = "Quantity of order cannot be null")
    @Positive(message = "Quantity of order must be positive number")
    private Integer quantity;

    private final LocalDateTime creationDateTime;

    @ManyToOne
    @NotNull(message = "Item of order cannot be null")
    private Item item;

    public Order() {
        this.creationDateTime = LocalDateTime.now();
    }

    public boolean isValid() {
        return this.creationDateTime.plusMinutes(10L).isBefore(LocalDateTime.now());
    }
}
