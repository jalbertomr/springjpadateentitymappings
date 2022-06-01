package com.bext.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="customer_seq")
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Item item;

    public Customer(String name) {
        this.name = name;
    }
}
