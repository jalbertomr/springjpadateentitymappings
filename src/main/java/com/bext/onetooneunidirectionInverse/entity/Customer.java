package com.bext.onetooneunidirectionInverse.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customer", schema = "dummy")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dummy.customer_seq")
    private Long id;
    private String name;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Item item;

}

