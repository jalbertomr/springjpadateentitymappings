package com.bext.onetoonebidirectable.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customer", schema = "dummy")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="dummy.customer_seq")
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL) // fetch default EAGER
    @JoinTable(name = "customer_item", schema = "dummy",
                joinColumns = { @JoinColumn(name = "customer_id")},       // could be omitted
                inverseJoinColumns = { @JoinColumn(name = "item_id")})    // could be omitted
    private Item item;

    public Customer(String name) {
        this.name = name;
    }
}

