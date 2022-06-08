package com.bext.manytomanyunidirection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "customer", schema = "dummy")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.customer_seq")
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL) @JoinTable(schema = "dummy")
    private Set<Item> items = new HashSet<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addItem(Item item){
        this.getItems().add(item);

    }

    public Set<Item> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }
}
