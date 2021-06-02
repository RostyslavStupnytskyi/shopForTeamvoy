package stupnytskyi.rostyslav.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stupnytskyi.rostyslav.shop.dto.request.ItemCreationRequest;
import stupnytskyi.rostyslav.shop.dto.request.ItemSearchRequest;
import stupnytskyi.rostyslav.shop.dto.request.ItemUpdateQuantityRequest;
import stupnytskyi.rostyslav.shop.dto.response.ItemResponse;
import stupnytskyi.rostyslav.shop.entity.Item;
import stupnytskyi.rostyslav.shop.repository.ItemRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void createAndSaveItemFromRequest(ItemCreationRequest itemCreationRequest) {
        saveItemInDatabase(mapItemCreationRequestToItem(itemCreationRequest));
    }

    private void saveItemInDatabase(Item item) {
        itemRepository.save(item);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No item with id " + id));
    }

    public boolean isItemEnableQuantityByItemId(Long itemId, Integer quantity) {
        return findById(itemId).isQuantityEnable(quantity);
    }

    public void minusValueFromItemQuantity(Item item, Integer quantity) {
        updateItemQuantityAndSave(item, item.getAvailableQuantity() - quantity);
    }

    public void updateItemQuantityAndSave(Item item, Integer newQuantity) {
        item.setAvailableQuantity(newQuantity);
        saveItemInDatabase(item);
    }

    public void updateItemQuantityAndSave(ItemUpdateQuantityRequest itemUpdateQuantityRequest) {
        Item item = findById(itemUpdateQuantityRequest.getItemId());
        updateItemQuantityAndSave(item, itemUpdateQuantityRequest.getQuantity());
    }

    private Item mapItemCreationRequestToItem(ItemCreationRequest itemCreationRequest) {
        Item item = new Item();
        item.setPrice(itemCreationRequest.getPrice());
        item.setAvailableQuantity(itemCreationRequest.getQuantity());
        item.setTitle(itemCreationRequest.getTitle());
        item.setMaker(itemCreationRequest.getMaker());
        return item;
    }


    public List<ItemResponse> findItemsBySearchRequest(String title, Integer quantity) {
        ItemSearchRequest itemSearchRequest = new ItemSearchRequest(title, quantity);
        if (isItemWithLowestPriceByTitleAvailableByQuantity(itemSearchRequest))
            return getSingleItemResponseListByItem(findItemWithLowestPriceByTitleContains(itemSearchRequest.getTitle()));
        else
            return getAvailableItemsResponseListBySearchValue(itemSearchRequest);
    }

    private List<ItemResponse> getSingleItemResponseListByItem(Item item) {
        return Collections.singletonList(new ItemResponse(item));
    }

    private List<ItemResponse> getAvailableItemsResponseListBySearchValue(ItemSearchRequest itemSearchRequest) {
        List<Item> items = findItemsByTitleContainsAndAvailableQuantityGreaterThan(itemSearchRequest);
        return items.stream()
                .filter(i -> i.isQuantityEnable(itemSearchRequest.getQuantity()))
                .map(ItemResponse::new)
                .collect(Collectors.toList());
    }


    private boolean isItemWithLowestPriceByTitleAvailableByQuantity(ItemSearchRequest itemSearchRequest) {
        return findItemWithLowestPriceByTitleContains(itemSearchRequest.getTitle()).isQuantityEnable(itemSearchRequest.getQuantity());
    }

    private List<Item> findItemsByTitleContainsAndAvailableQuantityGreaterThan(ItemSearchRequest itemSearchRequest) {
        return itemRepository.findAllByTitleContainsIgnoreCaseAndAvailableQuantityGreaterThanEqual(itemSearchRequest.getTitle(), itemSearchRequest.getQuantity());
    }

    private Item findItemWithLowestPriceByTitleContains(String title) {
        return itemRepository.findFirstByTitleContainsIgnoreCaseOrderByPriceAsc(title).orElseThrow(() -> new IllegalArgumentException("No item matches '" + title + "' request"));
    }


}
