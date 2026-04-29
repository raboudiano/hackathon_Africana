package com.Shadows.SpringZ.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {

    private String address;
    private String city;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
}