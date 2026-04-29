package com.Shadows.SpringZ.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "subcategory") 
    private List<Product> products;
}