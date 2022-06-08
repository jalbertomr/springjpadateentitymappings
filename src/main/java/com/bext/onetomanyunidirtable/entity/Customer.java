package com.bext.onetomanyunidirtable.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customer", schema = "dummy")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.customer_seq")
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_items", schema = "dummy",      // table can be omitted
            joinColumns = {@JoinColumn( name = "customer_id")},  // can be omitted
            inverseJoinColumns = {@JoinColumn(name= "item_id")}) // can be omitted
    private List<Item> items = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }


}
