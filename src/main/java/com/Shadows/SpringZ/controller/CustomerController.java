package com.Shadows.SpringZ.controller;

import com.Shadows.SpringZ.model.Customer;
import com.Shadows.SpringZ.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/addCustomer")
    public String addCustomer(Model model) {
        model.addAttribute("CustomerForm", new Customer());
        return "new_customer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("CustomerForm") Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/allCustomers";
    }

    @GetMapping("/allCustomers")
    public String listCustomers(Model model) {
        List<Customer> listCustomers = customerService.getAllCustomers();
        model.addAttribute("listCustomers", listCustomers);
        return "liste_customers";
    }

    @GetMapping("editCustomers/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Customer customer = customerService.getCustomerByID(id);
        model.addAttribute("customer", customer);
        return "update_customer";
    }

    @PostMapping("updateCustomers/{id}")
    public String updateCustomer(
            @PathVariable("id") long id,
            Customer customer,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "update_customer";
        }

        customerService.updateCustomer(customer);
        return "redirect:/allCustomers";
    }

    @GetMapping("deleteCustomers/{id}")
    public String deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return "redirect:/allCustomers";
    }
}
