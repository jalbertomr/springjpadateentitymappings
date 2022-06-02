package com.bext.onetooneunidirection.entity;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id_fk")      // default "item_id", could by omitted
    private Item item;                    // in DB -> item_id_fk    bigint H2

    public Customer(String name) {
        this.name = name;
    }
}

