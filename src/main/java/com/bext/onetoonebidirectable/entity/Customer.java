package com.bext.onetoonebidirectable.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "customer", schema = "dummy")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="dummy.customer_seq")
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL) // fetch default EAGER
    @JoinTable( name = "customer_item", schema = "dummy",
                joinColumns = { @JoinColumn(name = "customer_id")},       // could be omitted
                inverseJoinColumns = { @JoinColumn(name = "item_id")})    // could be omitted
    private Item item;

    public Customer(String name) {
        this.name = name;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void removeItem(){
        this.item.setCustomer(null);
        this.item = null;
    }

    public Item getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", item.name=" + item.getName() +
                '}';
    }
}

