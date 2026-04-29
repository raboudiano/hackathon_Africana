package com.Shadows.SpringZ.api;

import com.Shadows.SpringZ.api.dto.OrderRequest;
import com.Shadows.SpringZ.api.dto.OrderResponse;
import com.Shadows.SpringZ.model.Order;
import com.Shadows.SpringZ.model.Product;
import com.Shadows.SpringZ.service.CustomerService;
import com.Shadows.SpringZ.service.OrderService;
import com.Shadows.SpringZ.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JSON endpoints for Order CRUD.
 *
 * DTOs are used to avoid JSON recursion from bidirectional JPA mappings.
 */
@RestController
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderApiController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderApiController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping
    public List<OrderResponse> list() {
        return orderService.getAllOrders().stream()
                .map(OrderApiController::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Long id) {
        return toResponse(orderService.getOrderByID(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request) {
        Order order = new Order();
        apply(order, request);
        Order saved = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse update(@PathVariable Long id, @RequestBody OrderRequest request) {
        Order existing = orderService.getOrderByID(id);
        apply(existing, request);
        return toResponse(orderService.updateOrder(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    private void apply(Order order, OrderRequest request) {
        order.setRef(request.ref());
        order.setPrice(request.price());
        order.setDate(request.date());

        if (request.customerId() != null) {
            order.setCustomer(customerService.getCustomerByID(request.customerId()));
        } else {
            order.setCustomer(null);
        }

        if (request.productIds() != null && !request.productIds().isEmpty()) {
            order.setProducts(productService.getAllById(request.productIds()));
        } else {
            order.setProducts(List.of());
        }
    }

    private static OrderResponse toResponse(Order order) {
        Long customerId = order.getCustomer() == null ? null : order.getCustomer().getId();
        List<Long> productIds = order.getProducts() == null
                ? List.of()
                : order.getProducts().stream().map(Product::getId).toList();

        return new OrderResponse(
                order.getId(),
                order.getRef(),
                order.getPrice(),
                order.getDate(),
                customerId,
                productIds
        );
    }
}
