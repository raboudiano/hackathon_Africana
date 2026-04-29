package com.Shadows.SpringZ.controller;

import com.Shadows.SpringZ.model.Customer;
import com.Shadows.SpringZ.model.Order;
import com.Shadows.SpringZ.model.Product;
import com.Shadows.SpringZ.service.CustomerService;
import com.Shadows.SpringZ.service.OrderService;
import com.Shadows.SpringZ.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping("/addOrders")
    public String addOrders(Model model) {
        Order order = new Order();
        List<Customer> customers = customerService.getAllCustomers();
        List<Product> products = productService.getAllProducts();

        model.addAttribute("OrdersFrom", order);
        model.addAttribute("CustomersList", customers);
        model.addAttribute("ProductList", products);
        return "new_orders";
    }

    @PostMapping("/saveOrders")
    public String saveOrders(
            @ModelAttribute("OrdersFrom") Order order,
            @RequestParam(value = "products", required = false) List<Long> products
    ) {
        if (order.getCustomer() != null && order.getCustomer().getId() != null) {
            order.setCustomer(customerService.getCustomerByID(order.getCustomer().getId()));
        }

        if (products != null && !products.isEmpty()) {
            order.setProducts(productService.getAllById(products));
        }

        orderService.createOrder(order);
        return "redirect:/allOrders";
    }

    @GetMapping("/allOrders")
    public String listOrders(Model model) {
        List<Order> listOrders = orderService.getAllOrders();
        model.addAttribute("listorders", listOrders);
        return "liste_orders";
    }

    @GetMapping("editOrders/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Order order = orderService.getOrderByID(id);
        model.addAttribute("orders", order);
        model.addAttribute("customerList", customerService.getAllCustomers());
        model.addAttribute("productList", productService.getAllProducts());
        return "update_orders";
    }

    @PostMapping("updateOrders/{id}")
    public String updateOrders(
            @PathVariable("id") long id,
            Order order,
            BindingResult result,
            @RequestParam(value = "products", required = false) List<Long> products
    ) {
        if (result.hasErrors()) {
            order.setId(id);
            return "update_orders";
        }

        if (order.getCustomer() != null && order.getCustomer().getId() != null) {
            order.setCustomer(customerService.getCustomerByID(order.getCustomer().getId()));
        }

        if (products != null) {
            order.setProducts(productService.getAllById(products));
        }

        orderService.updateOrder(order);
        return "redirect:/allOrders";
    }

    @GetMapping("deleteOrders/{id}")
    public String deleteOrders(@PathVariable("id") long id) {
        orderService.deleteOrder(id);
        return "redirect:/allOrders";
    }
}
