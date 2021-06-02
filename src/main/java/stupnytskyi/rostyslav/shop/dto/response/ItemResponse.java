package stupnytskyi.rostyslav.shop.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stupnytskyi.rostyslav.shop.entity.Item;


@Getter
@Setter
public class ItemResponse {

    private Long id;
    private String title;
    private String maker;
    private Double price;
    private Integer availableQuantity;

    public ItemResponse(Item item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.maker = item.getMaker();
        this.price = item.getPrice();
        this.availableQuantity = item.getAvailableQuantity();
    }

    public ItemResponse() { // no args constructor for parse from JSON
    }

    @Override
    public String toString() {
        return "ItemResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", maker='" + maker + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}
