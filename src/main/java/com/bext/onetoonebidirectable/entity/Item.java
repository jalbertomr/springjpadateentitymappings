package com.bext.onetoonebidirectable.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "item", schema = "dummy")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.item_seq")
    private Long id;
    private String name;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private Customer customer;

    public Item(String name) {
        this.name = name;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void removeCustomer(){
       this.customer.setItem(null);
       this.customer = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customer.name=" + customer.getName() +
                '}';
    }
}
