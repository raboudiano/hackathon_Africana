package com.Shadows.SpringZ.service;

import com.Shadows.SpringZ.model.Order;

import java.util.List;

/**
 * Service Layer for Order CRUD.
 */
public interface OrderService {
    Order createOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderByID(Long id);

    Order updateOrder(Order order);

    void deleteOrder(Long id);
}
