package com.bext.onetooneunidirsharedkey.entity;

import javax.persistence.*;

@Entity
@Table(name="item", schema="dummy")
public class Item {

    @Id
    @Column(name="customer_id")
    private Long id;
    private String name;
    @OneToOne(fetch = FetchType.EAGER)   // To Try to update item id but dont work, done manually
    @MapsId            // add contraint foreign key (customer_id) references dummy.customer
    @JoinColumn(name ="customer_id")
    private Customer customer;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //(customer != null ? ", customer=" + customer : ",customer=null") +
                '}';
    }
}
