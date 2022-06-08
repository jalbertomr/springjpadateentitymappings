package com.bext.manytooneunidirection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customer", schema= "dummy")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.customer_seq")
    private Long id;
    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
