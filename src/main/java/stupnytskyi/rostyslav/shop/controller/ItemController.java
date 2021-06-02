package stupnytskyi.rostyslav.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stupnytskyi.rostyslav.shop.dto.request.ItemCreationRequest;
import stupnytskyi.rostyslav.shop.dto.request.ItemUpdateQuantityRequest;
import stupnytskyi.rostyslav.shop.dto.response.ItemResponse;
import stupnytskyi.rostyslav.shop.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    private void createItem(@RequestBody @Valid ItemCreationRequest itemCreationRequest) {
        itemService.createAndSaveItemFromRequest(itemCreationRequest);
    }

    @PutMapping("quantity")
    private void updateItemQuantity(@RequestBody @Valid ItemUpdateQuantityRequest itemUpdateQuantityRequest) {
        itemService.updateItemQuantityAndSave(itemUpdateQuantityRequest);
    }

    @GetMapping("search")
    private List<ItemResponse> searchItems(String title, Integer quantity) {
        return itemService.findItemsBySearchRequest(title, quantity);
    }
}
