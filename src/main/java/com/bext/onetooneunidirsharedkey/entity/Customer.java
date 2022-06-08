package com.bext.onetooneunidirsharedkey.entity;

import javax.persistence.*;

@Entity
@Table(name="customer", schema="dummy")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.customer_seq")
    @Column(name="customer_id")
    private Long id;
    private String name;
    @OneToOne(mappedBy = "customer",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)  // To Try to update item id but dont work, done manually
    private Item item;

    public Customer() {
    }

    public Customer(String name) {
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //(item != null ? ",item=" + item : ",item=null") +
                '}';
    }
}
