package stupnytskyi.rostyslav.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stupnytskyi.rostyslav.shop.dto.request.OrderCreationRequest;
import stupnytskyi.rostyslav.shop.dto.response.OrderResponse;
import stupnytskyi.rostyslav.shop.exceptions.OrderCannotBeDeletedException;
import stupnytskyi.rostyslav.shop.exceptions.OrderQuantityVeryBigException;
import stupnytskyi.rostyslav.shop.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    private void createOrder(@RequestBody @Valid OrderCreationRequest orderCreationRequest) throws OrderQuantityVeryBigException {
        orderService.createAndSaveOrderFromOrderCreationRequest(orderCreationRequest);
    }

    @DeleteMapping
    private void deleteOrder(Long id) throws OrderCannotBeDeletedException {
        orderService.deleteOrderById(id);
    }

    @GetMapping("all")
    private List<OrderResponse> findAll() {
        return orderService.findAllOrdersResponse();
    }
}
