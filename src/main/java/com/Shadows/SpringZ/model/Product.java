package com.Shadows.SpringZ.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory; 

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();
}