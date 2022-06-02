package com.bext.onetoonebidirectable.entity;

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

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private Customer customer;

    public Item(String name) {
        this.name = name;
    }
}
