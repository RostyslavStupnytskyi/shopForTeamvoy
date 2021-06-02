package stupnytskyi.rostyslav.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stupnytskyi.rostyslav.shop.dto.request.OrderCreationRequest;
import stupnytskyi.rostyslav.shop.dto.response.OrderResponse;
import stupnytskyi.rostyslav.shop.entity.Order;
import stupnytskyi.rostyslav.shop.exceptions.OrderCannotBeDeletedException;
import stupnytskyi.rostyslav.shop.exceptions.OrderQuantityVeryBigException;
import stupnytskyi.rostyslav.shop.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemService itemService;

    public void createAndSaveOrderFromOrderCreationRequest(OrderCreationRequest orderCreationRequest) throws OrderQuantityVeryBigException {
        validateOrderCreationRequestForItemQuantity(orderCreationRequest);
        Order order = mapOrderCreationRequestToOrder(orderCreationRequest);
        minusItemQuantityByOrder(order);
        saveOrderInDatabase(order);
    }

    private void saveOrderInDatabase(Order order) {
        orderRepository.save(order);
    }

    private void minusItemQuantityByOrder(Order order) {
        itemService.minusValueFromItemQuantity(order.getItem(), order.getQuantity());
    }

    private void validateOrderCreationRequestForItemQuantity(OrderCreationRequest orderCreationRequest) throws OrderQuantityVeryBigException {
        if (!itemService.isItemEnableQuantityByItemId(orderCreationRequest.getItemId(), orderCreationRequest.getQuantity()))
            throw new OrderQuantityVeryBigException();
    }

    public void deleteOrderById(Long id) throws OrderCannotBeDeletedException {
        Order order = findById(id);
        validateOrderForDeleting(order);
        deleteOrderFromDatabase(order);
    }

    public List<OrderResponse> findAllOrdersResponse() {
        return orderRepository.findAll().stream().map(OrderResponse::new).collect(Collectors.toList());
    }

    private void deleteOrderFromDatabase(Order order) {
        orderRepository.delete(order);
    }

    private Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order with id " + id + " doesn`t exist"));
    }

    private void validateOrderForDeleting(Order order) throws OrderCannotBeDeletedException {
        if (!order.isValid()) throw new OrderCannotBeDeletedException(order.getId());
    }

    private Order mapOrderCreationRequestToOrder(OrderCreationRequest orderCreationRequest) {
        Order order = new Order();
        order.setItem(itemService.findById(orderCreationRequest.getItemId()));
        order.setQuantity(orderCreationRequest.getQuantity());
        order.setPrice(order.getItem().getPrice() * order.getQuantity());
        return order;
    }
}
