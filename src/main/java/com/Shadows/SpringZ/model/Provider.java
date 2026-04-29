package com.Shadows.SpringZ.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Provider extends User {

    private String matricule;
    private String service;
    private String company;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Product> products;
}