package com.bext.manytooneunidirection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "item", schema = "dummy")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.item_seq")
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id_fk")
    private Customer customer;

    public Item(String name, Customer customer) {
        this.name = name;
        this.customer = customer;
    }

    public Item(String name) {
        this.name = name;
    }
}
